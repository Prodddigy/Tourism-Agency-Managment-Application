package com.example.mas_final_project.services;


import com.example.mas_final_project.model.StatusPack.*;
import com.example.mas_final_project.model.TourismPackage;
import com.example.mas_final_project.model.TripStatus;
import com.example.mas_final_project.repository.TourismPackageRepository;
import com.example.mas_final_project.repository.TripStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class TripStatusService {

    @Autowired
    private final TripStatusRepository tsr;
   @Autowired
    private final TourismPackageRepository tpr;

    public TripStatusService(TripStatusRepository tsr, TourismPackageRepository tpr) {
        this.tsr = tsr;
        this.tpr = tpr;
    }


    /**
     * this method changes a InPreparation status into a Prepared one
     * it required a tourism package objetc which has a status Inpreparation
     * and also requires max and min number of particpants
     *
     * @param tp TourismPackage with StatusInPreparation
     * @param min min number of participants
     * @param max max number of participants
     * @return returns freshly created StatusPrepared object
     */
    @Transactional
    public StatusPrepared changeToPrepared(TourismPackage tp,int min,int max)
    {
        Optional<TourismPackage> oldTp = tpr.findById(tp.getId());

        if(oldTp.isEmpty())
        {
            return null;
        }
        if(tp.getTripStatus() ==null)
        {
            return null;
        }

        if(tp.getGuides() ==null)
        {
            return null;
        }

        Optional<TripStatus> sip = tsr.getById(tp.getTripStatus().getId());
        if(sip.isEmpty())
        {
            return null;
        }

        if(!(sip.get() instanceof StatusInPreparation))
        {
            return null;
        }

        TourismPackage updatedTp = oldTp.get();

        StatusPrepared sp = StatusPrepared.builder()
                .maxParticipants(max)
                .minParticipants(min)
                .owner(updatedTp)
                .build();
        updatedTp.setTripStatus(sp);

        tpr.save(updatedTp);
        tsr.save(sp);

        return sp;
    }


    /**
     * If a Status is InPreparation it can be deleted forever not archived
     * @param tp TourismPackage with StatusInPreparation
     * @return return 1 if it was deleted, 0 if tp was not found, -1 if the status is not InPreparation
     */
    @Transactional
    public int deleteStatus(TourismPackage tp)
    {
        Optional<TourismPackage> oldTp = tpr.findById(tp.getId());
        if(oldTp.isEmpty())
        {
            return 0;
        }

        if(!(oldTp.get().getTripStatus() instanceof StatusInPreparation))
        {
            return -1;
        }

        oldTp.get().setTripStatus(null);

        tpr.save(tp);
        return 1;
    }


    /**
     * changes StatusPrepared or StatusReady to StatusCancelled
     *
     * @param tp TourismPackage with StatusPrepared or StatusReady
     * @param reasonDesc description exmplaining why it was canceled
     * @return 0  if oldTp or ts was not found, 1 if process succeded, -1 if Status was of
     * a different type than expected
     */
    @Transactional
    public int changeToCancelled(TourismPackage tp,String reasonDesc)
    {
        Optional<TourismPackage> oldTp = tpr.findById(tp.getId());

        if(oldTp.isEmpty())
        {
            return 0;
        }

        Optional<TripStatus> ts = tsr.getById(oldTp.get().getTripStatus().getId());



        if(ts.isEmpty())
        {
            return 0;
        }

        if(ts.get() instanceof StatusPrepared || (ts.get() instanceof StatusReady)) {

            StatusCancelled sc = StatusCancelled.builder()
                    .reasonDesc(reasonDesc)
                    .owner(oldTp.get())
                    .build();
            oldTp.get().setTripStatus(sc);

            tpr.save(oldTp.get());

            tsr.save(sc);
            return 1;
        }
        else
        {
            return -1;
        }
    }

    /**
     * Changes PreparedStatus to ReadyStatus, as long as
     * participants min and max constraints were met and as long as there is a
     * tourist guide
     * @param tp tourismpackage with ReadyStatus
     * @return null if trip or status is not found
     * freshly made StatusReady object if everything went correct
     */
    @Transactional
    public StatusReady changeToReady(TourismPackage tp)
    {
        Optional<TourismPackage> oldTp = tpr.findById(tp.getId());

        if(oldTp.isEmpty())
        {
            return null;
        }


        Optional<TripStatus> ts = tsr.getById(oldTp.get().getTripStatus().getId());

        if(ts.isEmpty())
        {
            return null;
        }

        if(!(ts.get() instanceof StatusPrepared))
        {
            return null;
        }

        int count = tpr.findClientsByTourismPackage(oldTp.get().getId());
        int min = ((StatusPrepared) ts.get()).getMinParticipants();
        int max  =((StatusPrepared) ts.get()).getMaxParticipants();
        double total = count * oldTp.get().getPrice();

        System.out.println(count+" number of clients on trip "+ oldTp.get().getName()+ " min size "+min);

        if(count<min || count>max)
        {
            System.out.println("min max");
            return null;
        }

        StatusReady sr = StatusReady.builder()
                .owner(oldTp.get())
                .totalGrossIncome(total)
                .build();

        oldTp.get().setTripStatus(sr);

        tpr.save(oldTp.get());
        tsr.save(sr);
        return sr;
    }


    /**
     * changes ReadyStatus to FinshedStatus
      * @param tp TourismPcakage that has ReadyStatus
     * @param descRep description/report of how the trip ended if it was ok
     * @return null if trip is not found or Status is missing or freshly made StatusFinished
     */
    @Transactional
    public StatusFinished changeToFinished(TourismPackage tp, String descRep)
    {
        Optional<TourismPackage> oldTp = tpr.findById(tp.getId());

        if(oldTp.isEmpty())
        {
            return null;
        }

        Optional<TripStatus> ts = tsr.getById(oldTp.get().getTripStatus().getId());
        if(ts.isEmpty())
        {
            return null;
        }

        StatusFinished sf = StatusFinished.builder()
                .reportDesc(descRep)
                .owner(oldTp.get())
                .build();
        oldTp.get().setTripStatus(sf);

        tpr.save(oldTp.get());

        tsr.save(sf);
        return sf;

    }



}
