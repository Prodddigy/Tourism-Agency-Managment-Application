package com.example.mas_final_project.GUI;

import com.example.mas_final_project.Controllers.GUIController;
import com.example.mas_final_project.MasFinalProjectApplication;
import com.example.mas_final_project.model.Client;
import com.example.mas_final_project.model.StatusPack.StatusPrepared;
import com.example.mas_final_project.model.TourismPackage;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

import java.awt.*;
import java.util.Arrays;

public class AddClientToTripGUI  {
    /**
     * this class contains all necessary GUI components to show addClientToATrip
     * use case
     */
    static Border border = new Border( new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,new BorderWidths(3)));

    static TableView<Client> clientTable;

    static TableView<TourismPackage> tripTable;

    static TableView<TourismPackage> clientTripTable;
    static ObservableList<TourismPackage> trips;

    static Client selectedClient;

    static TableView<Client> tripClientTable;
    static ObservableList<Client> clients;
    static TourismPackage selectedTrip;

    static Label clientName;
    static Label tripName;
    static Label chosenClientHeader;
    static Label chosenTripHeader;

    Label clientAddedDisappears;

    static Button addClient;

    //controllers
    public static GUIController gc;

    /**
     * this method initiased basic components like Labels,GridPane,button
     * also envoked other methods which create TableViews
     * @param stage
     * @param gc
     * @param MFPA
     */
    public void initialCreation(Stage stage, GUIController gc, MasFinalProjectApplication MFPA)
    {
        AddClientToTripGUI.gc = gc;
        stage.setTitle("TAMA™ Add client to a tourism package");

        this.clientAddedDisappears = new Label("Client added to trip\n successfully");
        clientAddedDisappears.setBackground(new Background(
                new BackgroundFill(Color.LIGHTGREEN,CornerRadii.EMPTY,Insets.EMPTY)
        ));

        clientAddedDisappears.setVisible(false);
        GridPane.setConstraints(clientAddedDisappears,2,2);

        Label allClientHeader = new Label("List of all clients");
        allClientHeader.setBorder(border);
        GridPane.setConstraints(allClientHeader,0,0);

        //general table of all clients existing in the DB

        createTableOfAllClients(gc);

        Label allTripsHeader = new Label("List of all trips");
        allTripsHeader.setBorder(border);
        GridPane.setConstraints(allTripsHeader,4,0);

        createTableOfAllPreparedTrips(gc);

        //client table header label
        chosenClientHeader = new Label("Client: ____, trips");
        chosenClientHeader.setBorder(border);
        GridPane.setConstraints(chosenClientHeader,1,2);

        createTableOfTripsOfSelectedClient();

        chosenTripHeader = new Label("Trip: ____, clients");
        chosenTripHeader.setBorder(border);
       // chosenTripHeader.setMaxWidth(200);
        GridPane.setConstraints(chosenTripHeader,3,2);

        createTableOfClientsOfSelectedTrip();

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        GridPane.setConstraints(tripClientTable,3,3);

        GridPane.setConstraints(clientTripTable,1,3);

        GridPane.setConstraints(clientTable,0,1);

        GridPane.setConstraints(tripTable,4,1);

        clientName = new Label("<- Choose client from the table \non your left");
        clientName.setBorder(border);
        GridPane.setConstraints(clientName,1,1);

        tripName = new Label("Choose tourism package ->\nfrom the table on your right");
        tripName.setBorder(border);
        GridPane.setConstraints(tripName,3,1);

        addClient = new Button("Add to trip ->");
        addClient.setMinWidth(100);
        addClient.setOnAction(MFPA);

        GridPane.setConstraints(addClient,2,1);

        grid.getChildren().addAll(
                allClientHeader,
                clientTable,clientName,
                chosenClientHeader,
                clientAddedDisappears,
                clientTripTable,addClient,tripName,
                chosenTripHeader,
                tripClientTable,
                allTripsHeader,
                tripTable);

        Scene scene = new Scene(grid,1100,500);

        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();
    }


    /**
     * this method creates and fills the TableView which contains
     * data about all clients in database
     * Additionally seperate rows can be selected for the whole use case
     * to work and updates Labels and a different TableView which are connected with this TableView
     * After selection it updates TableView with contents of Trips that are assigned to the selected Client
     * everytime with every new selection
     * @param gc
     */
    public static void createTableOfAllClients(GUIController gc)
    {
        TableColumn<Client, String> ForenameColumn = new TableColumn<>("Forename");
        ForenameColumn.setMinWidth(100);
        ForenameColumn.setCellValueFactory(new PropertyValueFactory<>("forename"));

        TableColumn<Client, String> SurnameColumn = new TableColumn<>("Surname");
        SurnameColumn.setMinWidth(100);
        SurnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));

        TableColumn<Client, String> dobColumn = new TableColumn<>("DOB");
        dobColumn.setMinWidth(100);
        dobColumn.setCellValueFactory(new PropertyValueFactory<>("dob"));

        TableColumn<Client, String> premiumColumn = new TableColumn<>("Premium?");
        premiumColumn.setMinWidth(100);
        premiumColumn.setCellValueFactory(new PropertyValueFactory<>("premium"));

        TableColumn<Client, String> ageColumn = new TableColumn<>("Age");
        Callback<TableColumn.CellDataFeatures<Client,String>, ObservableValue<String>> clientAgeCellValue;
        clientAgeCellValue = cellDataFeatures ->
        {
            Client client = cellDataFeatures.getValue();
            String age = String.valueOf(client.getAge());
            return (ObservableValue<String>) new SimpleStringProperty(age);
        };
        ageColumn.setMinWidth(40);
        ageColumn.setCellValueFactory(clientAgeCellValue);
        clientTable = new TableView<>();

        TableColumn<Client, String> AddressColumn = new TableColumn<>("Address");
        Callback<TableColumn.CellDataFeatures<Client,String>, ObservableValue<String>> clientAddressCellValue;
        clientAddressCellValue = cellDataFeatures ->
        {
            Client client = cellDataFeatures.getValue();
            String address = String.valueOf(client.getAddress());
            return (ObservableValue<String>) new SimpleStringProperty(address);
        };
        AddressColumn.setMinWidth(200);
        AddressColumn.setCellValueFactory(clientAddressCellValue);

        ObservableList<TableColumn<Client,?>> clientTableViewColumns =clientTable.getColumns();
        clientTableViewColumns.add(ForenameColumn);
        clientTableViewColumns.add(SurnameColumn);
        clientTableViewColumns.add(dobColumn);
        clientTableViewColumns.add(ageColumn);
        clientTableViewColumns.add(AddressColumn);
        clientTableViewColumns.add(premiumColumn);

        clientTable.setItems(gc.getAllClients());
        clientTable.setMaxHeight(200);
        clientTable.setMaxWidth(250);

        clientTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->
        {
            if(newValue !=null )
            {
                trips = FXCollections.observableArrayList();
                ObservableList<TourismPackage> ts = gc.getClientTrips(newValue.getId());

                if(ts != null)
                {
                    System.out.println(ts.size());
                    trips.addAll(ts);
                }
                clientTripTable.setItems(trips);

                chosenClientHeader.setText("Client: "+newValue.getForename()+" "+newValue.getSurname()+"'s, trips");

                clientName.setText("<|----------------------------------*"+
                        "\n<|"+newValue.getForename() +", "+newValue.getSurname()+
                        "\n<|Date of birth: "+newValue.getDob() +
                        "\n<|Age: "+newValue.getAge() +
                        "\n<|Address:" +
                        "\n<|"+newValue.getAddress().toString() +
                        "\n<|Premium client:"+newValue.isPremium() +
                        " \nV* Below are trips of selected client *V");
                selectedClient = newValue;
                System.out.println(selectedClient.getForename());
            }
        });
    }


    /**
     * this method creates TableView with contents of Clients corresponding to the selected Trip selected in
     * TableView containing all Trips that are already "Prepared" it gets udpdated with new values everytime
     * a new trip is selected new list of Clients gets inserted here
     */
    public static void createTableOfClientsOfSelectedTrip()
    {
        TableColumn<Client, String> tripClientNameColumn = new TableColumn<>("Forename");
        tripClientNameColumn.setMinWidth(100);
        tripClientNameColumn.setCellValueFactory(new PropertyValueFactory<>("forename"));

        TableColumn<Client, String> tripClientSurnameColumn = new TableColumn<>("Surname");
        tripClientSurnameColumn.setMinWidth(100);
        tripClientSurnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));

        TableColumn<Client, String> tripClientDobColumn = new TableColumn<>("DOB");
        tripClientDobColumn.setMinWidth(100);
        tripClientDobColumn.setCellValueFactory(new PropertyValueFactory<>("dob"));

        TableColumn<Client, String> AddressColumn = new TableColumn<>("Address");
        Callback<TableColumn.CellDataFeatures<Client,String>, ObservableValue<String>> clientAddressCellValue;
        clientAddressCellValue = cellDataFeatures ->
        {
            Client client = cellDataFeatures.getValue();
            String address = String.valueOf(client.getAddress());
            return (ObservableValue<String>) new SimpleStringProperty(address);
        };
        AddressColumn.setMinWidth(200);
        AddressColumn.setCellValueFactory(clientAddressCellValue);

        tripClientTable = new TableView<>();
        tripClientTable.setMaxHeight(200);
        tripClientTable.setMaxWidth(200);
        tripClientTable.getColumns().addAll(tripClientNameColumn,tripClientSurnameColumn,tripClientDobColumn,AddressColumn);
    }

    /**
     * another method that creates and fills A TableView
     * this time this table contains records about trips which are corresponding to the Client
     * selected in TableView created by `createTableOfAllClients()`
     *
     * */

    public static void createTableOfTripsOfSelectedClient()
    {
        TableColumn<TourismPackage, String> clientTripNameColumn = new TableColumn<>("Name");
        clientTripNameColumn.setMinWidth(150);
        clientTripNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<TourismPackage, String> clientTripPriceColumn = new TableColumn<>("Price");
        clientTripPriceColumn.setMinWidth(45);
        clientTripPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<TourismPackage, String> clientTripTransportColumn = new TableColumn<>("Transport(s)");
        clientTripTransportColumn.setMinWidth(130);
        clientTripTransportColumn.setCellValueFactory(new PropertyValueFactory<>("transportTypes"));

        TableColumn<TourismPackage, String> clientTripdateFromColumn = new TableColumn<>("DateFrom");
        clientTripdateFromColumn.setMinWidth(80);
        clientTripdateFromColumn.setCellValueFactory(new PropertyValueFactory<>("dateFrom"));

        TableColumn<TourismPackage, String> clientTripdateToColumn = new TableColumn<>("DateTo");
        clientTripdateToColumn.setMinWidth(80);
        clientTripdateToColumn.setCellValueFactory(new PropertyValueFactory<>("dateTo"));

        TableColumn<TourismPackage, String> clientTripStatusColumn = new TableColumn<>("Status");
        Callback<TableColumn.CellDataFeatures<TourismPackage,String>, ObservableValue<String>> clientTripStatusCellValue;
        clientTripStatusCellValue = cellDataFeatures ->
        {
            TourismPackage tp = cellDataFeatures.getValue();
            String address = String.valueOf(tp.getTripStatus().getClass().getSimpleName());
            return (ObservableValue<String>) new SimpleStringProperty(address);
        };
        clientTripStatusColumn.setCellValueFactory(clientTripStatusCellValue);
        clientTripTable = new TableView<>();

        clientTripTable.setMaxHeight(200);
        clientTripTable.setMaxWidth(200);
        clientTripTable.getColumns().addAll(clientTripNameColumn,
                clientTripPriceColumn,clientTripdateFromColumn,clientTripdateToColumn,
                clientTripTransportColumn ,clientTripStatusColumn);

    }

    /**
     * this one creates TableView of all Trips in DB that are of StatusPrepared
     * from which user may choose a trip and see its clients in a different TableView
     *
     * @param gc
     */
    public static void createTableOfAllPreparedTrips(GUIController gc)
    {
        TableColumn<TourismPackage, String> tripNameColumn = new TableColumn<>("Name");
        tripNameColumn.setMinWidth(150);
        tripNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<TourismPackage, String> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(45);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<TourismPackage, String> tripTransportColumn = new TableColumn<>("Transport(s)");
        tripTransportColumn.setMinWidth(130);
        tripTransportColumn.setCellValueFactory(new PropertyValueFactory<>("transportTypes"));

        TableColumn<TourismPackage, String> dateFromColumn = new TableColumn<>("DateFrom");
        dateFromColumn.setMinWidth(80);
        dateFromColumn.setCellValueFactory(new PropertyValueFactory<>("dateFrom"));

        TableColumn<TourismPackage, String> dateToColumn = new TableColumn<>("DateTo");
        dateToColumn.setMinWidth(80);
        dateToColumn.setCellValueFactory(new PropertyValueFactory<>("dateTo"));

        TableColumn<TourismPackage, String> descColumn = new TableColumn<>("Description");
        descColumn.setMinWidth(200);
        descColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        TableColumn<TourismPackage, String> tripTypesColumn = new TableColumn<>("TripType(s)");
        tripTypesColumn.setMinWidth(200);
        tripTypesColumn.setCellValueFactory(new PropertyValueFactory<>("tripTypes"));

        tripTable = new TableView<>();
        tripTable.setItems(gc.getPreparedTrips());
        tripTable.setMaxHeight(200);
        tripTable.setMaxWidth(250);

        tripTable.getColumns().addAll(tripNameColumn,priceColumn,dateFromColumn,dateToColumn,descColumn, tripTransportColumn,tripTypesColumn);

        tripTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->
        {
            if(newValue !=null && newValue.getTripStatus() instanceof StatusPrepared)
            {
                clients = FXCollections.observableArrayList();
                ObservableList<Client> c = gc.getClientsFromTrip(newValue.getId());
                if(c!=null) {
                    clients.addAll(c);
                }
                tripClientTable.setItems(clients);

                chosenTripHeader.setText("Trip: "+newValue.getName());

                tripName.setText(describeTrip(newValue));
                selectedTrip = newValue;
            }
        });
    }


    /**
     * this method handles the operation of clicking a button "add client to trip->"
     * it check whether the capacity of max aren't crossed
     * it checks whether a person is too young or too old for a camp
     * it checks whether a client isn't already in a trip
     * if any of the above are triggered a warning window pops out
     *
     * furthermore when all is perfromed correctly it shows a label for 5 sec about succeful
     * insertion of client to the trip
     * @param event
     */
    public void addClientEventButton(Event event)
    {
        if(event.getSource() == addClient)
        {
            if(selectedTrip != null && selectedClient != null )
            {

                if(selectedTrip.getTripTypes().contains(TourismPackage.TripType.Camp))
                {
                   // System.out.println("enters if for Camp");
                    if(!(selectedTrip.getAgeFrom()<=selectedClient.getAge()
                            && selectedTrip.getAgeTo()>= selectedClient.getAge()))
                    {
                        alertCreation("Client no applicable due to age constraint!",selectedClient.getForename()+ " is too young or too old for this camp!!!");
                        return;
                    }
                }

                if(!(gc.getMaxParticipants(selectedTrip)>=tripClientTable.getItems().size()+1))
                {
                    alertCreation("Reached max capacity in a trip!",selectedTrip.getName()+ " has reached its limit of max participants!!!");
                    return;
                }
                //set constraint to not make duplicate connections
                if(!clientTripTable.getItems().contains(selectedTrip)) {

                    System.out.println(Arrays.toString(clientTripTable.getItems().toArray()));
                    //here exchange objects between trip and a client

                    gc.addClientToTrip(selectedClient.getId(),selectedTrip.getId());
                    //here update table with list of clients
                    clients = FXCollections.observableArrayList();

                    clients.addAll(gc.getClientsFromTrip(selectedTrip.getId()));

                    tripClientTable.setItems(clients);

                    //here update table with list of trips
                    trips = FXCollections.observableArrayList();

                    trips.addAll(gc.getClientTrips(selectedClient.getId()));

                    clientTripTable.setItems(trips);
                    //update tripName Label with updated description
                    tripName.setText(describeTrip(selectedTrip));
                    clientAddedDisappears.setVisible(true);
                    Timeline tl = new Timeline(new KeyFrame(Duration.seconds(5), event1 ->

                            clientAddedDisappears.setVisible(false)));
                    tl.play();
                }
                else
                {
                    alertCreation("Client is in the trip!",selectedClient.getForename()+" is already enrolled on this trip: "+selectedTrip.getName());
                }
            }
        }
    }

    /**
     * a small toString method mayde so the number of currently added
     * participants is updated could be changed i guess
     * @param newValue
     * @return
     */
    public static String describeTrip(TourismPackage newValue)
    {
        return "*----------------------------------->" +
                "\n|-"+newValue.getName() +
                "\n|-Price per person:"+newValue.getPrice() +
                ((newValue.getTripTypes().contains(TourismPackage.TripType.Camp))?"\n|-Min age:"+newValue.getAgeFrom() +",Max age:"+newValue.getAgeTo():"")+
                ((newValue.getTripTypes().contains(TourismPackage.TripType.Pilgrimage))?"\n|-Relligion:"+newValue.getReligion():"")+
                ((newValue.getTripTypes().contains(TourismPackage.TripType.Mountain_Hike)&&newValue.getEquipmentReq() !=null)?"\n|-Required Equipment: YES":"")+
                "\n|-Transport(s):"+newValue.getTransportTypes().toString() +
                "\n|-Start:"+newValue.getDateFrom() +"-End:"+newValue.getDateTo() +
                "\n|-Board:"+newValue.getBoard() +
              //  "\n|-Trip Type(s):"+newValue.getTripTypes().toString() +
                "\n|-Current clients enrolled:"+ (long) clients.size() +", max:"+((StatusPrepared) newValue.getTripStatus()).getMaxParticipants()+
                "\n| V* Below are enrolled clients *V";
    }

    /**
     * warning window method to make it more generic and so
     * less repetion of code appears
     * @param title of the window
     * @param desc of the problem/warning
     */
    public static void alertCreation(String title, String desc)
    {
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setTitle(title);
        a.setContentText(desc);
        a.show();
    }
}
