package com.example.mas_final_project;

import com.example.mas_final_project.Controllers.*;
import com.example.mas_final_project.GUI.AddClientToTripGUI;
import com.example.mas_final_project.model.Client;
import com.example.mas_final_project.model.TourGuide;
import com.example.mas_final_project.model.TourismPackage;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.awt.*;
import java.time.LocalDate;
import java.util.Arrays;


@SpringBootApplication
public class MasFinalProjectApplication extends Application implements EventHandler {

	private ConfigurableApplicationContext springContext;
	private GUIController gc;
	private TourismPackageController tpc;
	private TourGuideController tgc;
	private ClientController cc;
	private TripStatusController tsc;

	public static void main(String[] args) {
//		SpringApplication.run(MasFinalProjectApplication.class, args);
		launch(args);
	}

	@Override
	public void init()
	{
		springContext = SpringApplication.run(MasFinalProjectApplication.class);
		gc = springContext.getBean(GUIController.class);
		tpc =springContext.getBean(TourismPackageController.class);
		tsc = springContext.getBean(TripStatusController.class);
		tgc = springContext.getBean(TourGuideController.class);
		cc = springContext.getBean(ClientController.class);
	}

	static AddClientToTripGUI gui;


	/**
	 *
	 * @param stage the primary stage for this application, onto which
	 * the application scene can be set.
	 * Applications may create other stages, if needed, but they will not be
	 * primary stages.
	 * @throws Exception
	 */
	@Override
	public void start(Stage stage) throws Exception {
		/**
		 * method under tests functionality of the dynamic objects of StatusTrip
		 * when played around it is safer to open and reopen the app in memory DB mode
		 */
	//	testDynamicTripStatus();

 		gui = new AddClientToTripGUI();
		gui.initialCreation( stage, gc,this);

		/**
		 * method under tests functionalities of various methods
		 */
		//testMethodsServices();

	}

	@Override
	public void handle(Event event) {

		gui.addClientEventButton(event);
	}

	@Override
	public void stop() throws Exception
	{
		springContext.close();
	}

	public void testMethodsServices()
	{
		System.out.println(
				cc.giveRandomBonus(4L)+ "bonus for Client"
		);

		System.out.println(
				tgc.giveRandomBonus(1L) + "bonus for Tour guide Damian"
		);

		System.out.println(
				tgc.showExperiencedGuides().toString()
		);

		System.out.println(
				tpc.getTripsByDates(
						LocalDate.parse("2024-08-15"),LocalDate.parse("2024-11-15")).size() + "how many trips"
		);
	}

	public void testDynamicTripStatus()
	{
		System.out.println(
				tsc.changeToPrepared(
						tpc.getTripByName("Mountain Camp Winter 2024 Śnieżka"),0,8).getMaxParticipants()+" MAX");
//		System.out.println(
//				tsc.deleteStatusInPreparation(
//						tpc.getTripByName("Mountain Hike Fall 2024 Śnieżka"))+"status number?");
//		System.out.println(
//				tsc.changeToStatusCancelled(
//						tpc.getTripByName("Mountain Camp Winter 2024 Śnieżka"),
//						"This combination of trip types was bad"
//				) + " is it cancelled?"
//		);

		gc.addClientToTrip(4L,tpc.getTripByName("Mountain Camp Winter 2024 Śnieżka").getId());

		System.out.println(
				tsc.changeToStatusReady(
						tpc.getTripByName("Mountain Camp Winter 2024 Śnieżka")
				) +" it changed to ready? "
		);
//
//				System.out.println(
//				tsc.changeToStatusCancelled(
//						tpc.getTripByName("Mountain Camp Winter 2024 Śnieżka"),
//						"This combination of trip types was bad"
//				) + " is it cancelled?"
//		);

		System.out.println(
				tsc.changeToStatusFinished(tpc.getTripByName("Mountain Camp Winter 2024 Śnieżka"),
						"it was okay every one liked it")
				+ "it changed to finished?"
		);

	}
}
