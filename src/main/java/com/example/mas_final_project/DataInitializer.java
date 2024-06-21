package com.example.mas_final_project;


import com.example.mas_final_project.model.*;
import com.example.mas_final_project.model.StatusPack.StatusInPreparation;
import com.example.mas_final_project.model.StatusPack.StatusPrepared;
import com.example.mas_final_project.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private final InsuranceRepository insuranceRepository;
    private final TourGuideRepository tourGuideRepository;
    private final TripStepRepository tripStepRepository;

    private final AccommodationRepository accommodationRepository;
    private final ReservationRepository reservationRepository;
    private final TourismPackageRepository tourismPackageRepository;

    private final TripStatusRepository tripStatusRepository;

    private final ClientRepository clientRepository;

    private final StaticAttributesRepository staticAttributesRepository;

    private static final Logger LOG = LoggerFactory.getLogger(DataInitializer.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        System.out.println("context refresher");

        initData();
    }

    @Transactional
    public void initData()
    {
        //if count table !=0 nie doawaj
        Address address = Address.builder()
                .zip("05-800")
                .street("Kolorwoa 27")
                .city("Warszawa")
                .build();
        Address address1 = Address.builder()
                .zip("05-800")
                .street("Kolorwoa 27")
                .city("Łódź")
                .build();
        Address address2 = Address.builder()
                .zip("05-800")
                .street("Kolorwoa 27")
                .city("Poznań")
                .build();
        Address address3 = Address.builder()
                .zip("05-800")
                .street("Kolorwoa 27")
                .city("Częstochowa")
                .build();

        StaticAttributes sa = StaticAttributes.builder()
                .premiumDiscount(100)
                .minPrice(70)
                .build();
        if(staticAttributesRepository.count() ==0)
        {
            staticAttributesRepository.save(sa);
        }
        TourismPackage.setPremiumDiscount(staticAttributesRepository.getPremiumDiscountById(1L));
        TourismPackage.setMinPrice(staticAttributesRepository.getMinPriceById(1L));


        TourismPackage tp4 = TourismPackage.builder()
                .name("Mountain Camp Winter 2024 Śnieżka")
                .tripTypes(new HashSet<>(Set.of(TourismPackage.TripType.Camp, TourismPackage.TripType.Mountain_Hike)))
                .equipmentReq("we need mountain gear like: pickaxe,heavy duty boots etc.")
                //  .religion("Christianity")
                .ageFrom(8)
                .ageTo(16)
                .description("This trip is a Mountain Hike Camp to the Śnieżka.")
                .dateFrom(LocalDate.parse("2024-11-15"))
                .dateTo(LocalDate.parse("2024-11-15"))
                .board(TourismPackage.Board.SC)
                .transportTypes(new ArrayList<>(Arrays.asList("Coach","Train")))
                .price(200)
                .build();

        TourismPackage tp3 = TourismPackage.builder()
                .name("Mountain Hike Fall 2024 Śnieżka")
                .tripTypes(new HashSet<>(Set.of(TourismPackage.TripType.Mountain_Hike)))
                .equipmentReq("we need mountain gear like: pickaxe,heavy duty boots etc.")
                //  .religion("Christianity")
//                .ageFrom(8)
//                .ageTo(10)
                .description("This trip is a Mountain Hike to the Śnieżka.")
                .dateFrom(LocalDate.parse("2024-10-08"))
                .dateTo(LocalDate.parse("2024-10-15"))
                .board(TourismPackage.Board.SC)
                .transportTypes(new ArrayList<>(Arrays.asList("Coach")))
                .price(200)
                .build();

        TourismPackage tp2 = TourismPackage.builder()
                .name("Camp Fall 2024 Częstochowa")
                .tripTypes(new HashSet<>(Set.of(TourismPackage.TripType.Camp)))
                //  .religion("Christianity")
                .ageFrom(8)
                .ageTo(14)
                .description("This trip is a Camp in częstochowa.")
                .dateFrom(LocalDate.parse("2024-09-15"))
                .dateTo(LocalDate.parse("2024-09-15"))
                .board(TourismPackage.Board.SC)
                .transportTypes(new ArrayList<>(Arrays.asList("Coach")))
                .price(200)
                .build();
        TourismPackage tp = TourismPackage.builder()
                .name("Pilgrimage Summer 2024 Jasna Góra")
                .tripTypes(new HashSet<>(Set.of(TourismPackage.TripType.Pilgrimage)))
                .religion("Christianity")
                .description("This trip is a pilgrimage to the city of Częstochowa.")
                .dateFrom(LocalDate.parse("2024-08-15"))
                .dateTo(LocalDate.parse("2024-08-15"))
                .board(TourismPackage.Board.SC)
                .transportTypes(new ArrayList<>(Arrays.asList("Coach")))
                .price(200)
                .build();

        if(tourismPackageRepository.count()==0)
        {
            tourismPackageRepository.save(tp4);
            tourismPackageRepository.save(tp3);
            tourismPackageRepository.save(tp2);
            tourismPackageRepository.save(tp);
        }

        Insurance insurance = Insurance.builder()
                .companyName("Awizo")
                .price(55)
                .description("Insurance for trip outside your nation and equipment insurance in case of " +
                        "breaking it or loosing it - Your insurance is our promise!")
                .type("Unfortunate accidents and equipment")
                .build();

        if(insuranceRepository.count() ==0)
        {
            insuranceRepository.save(insurance);
            tp.getInsurances().add(insurance);
            insuranceRepository.save(insurance);
            tourismPackageRepository.save(tp);

        }

        Accommodation ac = Accommodation.builder()
                .name("Villa Vacanza")
                .address(address)
                .type("guest house")
                .build();

        if(accommodationRepository.count() ==0)
        {
            accommodationRepository.save(ac);
        }

        TourGuide tg = TourGuide.builder()
                .forename("Damian")
                .surname("Czarnecki")
                .dob(LocalDate.parse("2001-01-27"))
                .expertTopics(new ArrayList<>(Arrays.asList("cooltopic")))
                .experienceLevel("well this is a very experienced guide in being unexperienced")
                .build();

        if(tourGuideRepository.count()==0)
        {
            tourGuideRepository.save(tg);

            tg.getTrips().add(tp);
            tourGuideRepository.save(tg);
            tp.setGuides(tg);
            tourismPackageRepository.save(tp);

            tg.getTrips().add(tp2);
            tourGuideRepository.save(tg);
            tp2.setGuides(tg);
            tourismPackageRepository.save(tp);

            tg.getTrips().add(tp4);
            tourGuideRepository.save(tg);
            tp4.setGuides(tg);
            tourismPackageRepository.save(tp4);
        }

        Reservation re = Reservation.builder()
                .accommodation(ac)
                .tourismPackage(tp)
                .dateFrom(LocalDate.parse("2024-08-10"))
                .dateTo(LocalDate.parse("2024-08-15"))
                .build();
        if(reservationRepository.count()==0)
        {
            reservationRepository.save(re);

            tp.getReservations().add(re);
            ac.getReservationSet().add(re);
            tourismPackageRepository.save(tp);
            accommodationRepository.save(ac);
            reservationRepository.save(re);
        }


       TripStep ts = TripStep.builder()
                .step(1)
                .name("Meet at meeting spot")
                .description("When all clients appear on the meet spot trip may start with riding the coach")
                .owner(tp)
                .build();

        if(tripStepRepository.count()==0)
        {
            tripStepRepository.save(ts);
            tp.getSteps().add(ts);
            tourismPackageRepository.save(tp);
            tripStepRepository.save(ts);
        }



        TripStatus sip = StatusInPreparation.builder()
                .importanceLevel("normal importance")
                .owner(tp4)
                .build();

        TripStatus sp2 = StatusPrepared.builder()
                .owner(tp)
                .maxParticipants(4)
                .minParticipants(2)
                .build();

        TripStatus sp3 = StatusPrepared.builder()
                .owner(tp2)
                .maxParticipants(2)
                .minParticipants(2)
                .build();

        TripStatus sip2 = StatusInPreparation.builder()
                .owner(tp3)
                .importanceLevel("not important can be deleted guys")
                .build();

        if(tripStatusRepository.count()==0)
        {
            tripStatusRepository.save(sip);
            tripStatusRepository.save(sp2);
            tripStatusRepository.save(sp3);

            tp2.setTripStatus(sp3);
            tourismPackageRepository.save(tp2);

            tripStatusRepository.save(sp3);

            tp.setTripStatus(sp2);
            tourismPackageRepository.save(tp);

            tripStatusRepository.save(sp2);

            tp4.setTripStatus(sip);
            tourismPackageRepository.save(tp4);
            tripStatusRepository.save(sip);

            tp3.setTripStatus(sip2);
            tourismPackageRepository.save(tp3);
            tripStatusRepository.save(sip2);
        }

        Client c1 = Client.builder()
                .forename("Damian")
                .surname("Czarnecki")
                .dob(LocalDate.parse("2001-01-27"))
                .address(address1)
                .premium(false)
                .build();
        Client c2 = Client.builder()
                .forename("John")
                .surname("Wick")
                .dob(LocalDate.parse("1970-01-27"))
                .address(address2)
                .premium(false)
                .build();
        Client c3 = Client.builder()
                .forename("Mike")
                .surname("Wazowski")
                .dob(LocalDate.parse("2013-08-01"))
                .address(address3)
                .premium(true)
                .build();

        Client c4 = Client.builder()
                .forename("John")
                .surname("Carmac")
                .dob(LocalDate.parse("1970-08-21"))
                .address(address3)
                .premium(true)
                .build();

        if(clientRepository.count()==0)
        {
            clientRepository.save(c1);
            clientRepository.save(c2);
            clientRepository.save(c3);
            clientRepository.save(c4);


        }

        entityManager.flush();

        LOG.info("Data initializer");

    }
}
