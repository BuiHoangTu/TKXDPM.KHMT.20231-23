package hust.mssv20200547.pttkhtaims.views;

import hust.mssv20200547.pttkhtaims.AIMS;
import hust.mssv20200547.pttkhtaims.controllers.OrderController;
import hust.mssv20200547.pttkhtaims.models.Media;
import hust.mssv20200547.pttkhtaims.models.Order;
import hust.mssv20200547.pttkhtaims.models.DeliveryInfo;
import hust.mssv20200547.pttkhtaims.interfaces.order.*;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.sql.SQLException;
import java.util.*;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.beans.property.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.input.MouseEvent;
import javafx.collections.ObservableMap;
import javafx.scene.text.Text;
import javafx.scene.control.*;

public class OrderManageView extends BaseView implements Initializable {
    private static final URL PATH = OrderManageView.class.getResource("/fxml/manage-order.fxml");
    private FXMLLoader fxmlLoader; // Thêm trường này
    private OrderController orderController = new OrderController();

    @FXML
    private TableView<Order> orderTableView;

    @FXML
    private TableColumn<Order, Integer> orderIdColumn;

    @FXML
    private TableColumn<Order, String> deliveryInfoColumn;

    @FXML
    private TableColumn<Order, String> orderStatusColumn;

    @FXML
    private TableView<Map.Entry<Media, Long>> detailOrder;

    @FXML
    private TableColumn<Map.Entry<Media, Long>, Long> idMedia;

    @FXML
    private TableColumn<Map.Entry<Media, Long>, String> name;

    @FXML
    private TableColumn<Map.Entry<Media, Long>, Long> quantity;
    @FXML
    private TableColumn<Map.Entry<Media, Long>, Long> value;
    @FXML
    private TableColumn<Map.Entry<Media, Long>, Long> totalPrice;

    @FXML
    private Text receiverName;
    @FXML
    private Text receiverEmail;
    @FXML
    private Text receiverPhone;
    @FXML
    private Text receiverProvince;
    @FXML
    private Text receiverAddress;
    @FXML
    private Text receiverInstruction;
    @FXML
    private Button acceptButton;
    @FXML
    private Button rejectButton;
    @FXML
    private Button updateButton;

    public OrderManageView() throws IOException {
        super(PATH);
        fxmlLoader = new FXMLLoader(PATH);
        fxmlLoader.setController(this);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            orderTableView.setOnMouseClicked((MouseEvent event) -> {
                if (event.getClickCount() == 1) {
                    // Lấy dữ liệu của hàng được chọn
                    var selectedRow = orderTableView.getSelectionModel().getSelectedItem();

                    try {
                        updateOrderStatus(selectedRow.getOrderId(), selectedRow);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    ObservableMap<Media, Long> observableMap = FXCollections.observableMap(selectedRow.getMediaInOrder());
                    // Chuyển đổi ObservableMap thành danh sách ObservableList
                    ObservableList<Map.Entry<Media, Long>> data = FXCollections.observableArrayList(observableMap.entrySet());

                    if (selectedRow != null) {
                        idMedia.setCellValueFactory(cellData ->
                                new SimpleObjectProperty<>(cellData.getValue().getKey().getId())
                        );
                        name.setCellValueFactory(cellData ->
                                new SimpleObjectProperty<>(cellData.getValue().getKey().getTitle())
                        );
                        value.setCellValueFactory(cellData ->
                                new SimpleObjectProperty<>(cellData.getValue().getKey().getValue())
                        );

                        quantity.setCellValueFactory(cellData ->
                                new SimpleObjectProperty<>(cellData.getValue().getValue())
                        );
                        totalPrice.setCellValueFactory(cellData -> {
                            Media media = cellData.getValue().getKey();
                            Long quantity = cellData.getValue().getValue();
                            Long totalPrice = media.getValue() * quantity;
                            return new SimpleObjectProperty<>(totalPrice);
                        });
                        detailOrder.setItems(data);

                        // Set value cho các giá trị của thông tin người nhận
                        receiverName.setText(selectedRow.getDeliveryInfo().getReceiver());
                        receiverEmail.setText(selectedRow.getDeliveryInfo().getEmail());
                        receiverPhone.setText(selectedRow.getDeliveryInfo().getPhoneNumber());
                        receiverProvince.setText(selectedRow.getDeliveryInfo().getCityAddress());
                        receiverAddress.setText(selectedRow.getDeliveryInfo().getDetailedAddress());
                        receiverInstruction.setText(selectedRow.getDeliveryInfo().getInstruction());
                    }
                }
            });

            // Lấy dữ liệu đổ vào bảng danh sách đơn hàng
            setDataOfTableOrder();

        } catch (SQLException e) {
            // Handle the SQLException appropriately
            e.printStackTrace(); // or log the exception
        }
    }

    @Override
    public OrderController getController() {
        return (OrderController) super.getController();
    }

    public void switchToOrderManageView() {

        try {
            // Sử dụng fxmlLoader đã được tạo trước đó
            Parent orderManageViewParent = fxmlLoader.load();

            // Tạo một Scene mới với Parent làm nội dung
            Scene orderManageScene = new Scene(orderManageViewParent);

            // Tạo một Stage mới
            Stage orderManageStage = new Stage();

            // Thiết lập Stage với Scene và tiêu đề
            orderManageStage.setScene(orderManageScene);
            orderManageStage.setTitle("Order Management");

            orderManageStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void updateOrderStatus(int id, Order selectedRow) throws SQLException {

        acceptButton.setOnAction(e -> {
            // Tạo hộp thoại xác nhận
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xác nhận");
            alert.setHeaderText("Xác nhận");
            alert.setContentText("Bạn có chắc chắn muốn chấp nhận đơn hàng này?");

            // Nhận kết quả từ hộp thoại xác nhận
            Optional<ButtonType> result = alert.showAndWait();

            // Kiểm tra xem người dùng đã ấn OK hay không
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    var checkedString = orderController.checkAvailable(selectedRow.getMediaInOrder());
                    if (checkedString.length() == 0) {
                        // Thực hiện cập nhật trạng thái đơn hàng
                        orderController.updateOrderStatus(id, Order.OrderStatus.ACCPEPT);

                        // Cập nhật dữ liệu cho TableView
                        setDataOfTableOrder();
                    }
                    else {
                        Alert alert1 = new Alert(Alert.AlertType.ERROR);
                        alert1.setTitle("Thông báo");
                        alert1.setContentText(checkedString);
                        alert1.show();  // Hiển thị alert
                    }


                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        rejectButton.setOnAction(e -> {
            // Tạo hộp thoại xác nhận
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xác nhận");
            alert.setHeaderText("Xác nhận");
            alert.setContentText("Bạn có chắc chắn muốn từ chối đơn hàng này?");

            // Nhận kết quả từ hộp thoại xác nhận
            Optional<ButtonType> result = alert.showAndWait();

            // Kiểm tra xem người dùng đã ấn OK hay không
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    // Thực hiện cập nhật trạng thái đơn hàng
                    orderController.updateOrderStatus(id, Order.OrderStatus.CANCELED);

                    // Cập nhật dữ liệu cho TableView
                    setDataOfTableOrder();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    public void setDataOfTableOrder() throws SQLException {
        List<Order> listOfOrders = orderController.getListOrders(1, 1);

        for (int i = 0; i < listOfOrders.size(); i++) {
            Order order = listOfOrders.get(i);
            var id = order.getOrderStatus().getI();
            IOrderStatusStrategy orderStatusStrategy = OrderStatusStrategyFactory.CreateStrategy(id);
            System.out.println(orderStatusStrategy.getStatusDescription());
            order.setOrderStatusStrategy(orderStatusStrategy);
//            order = new Order(order.getOrderId(), order.getMediaInOrder(), order.getDeliveryInfo(), order.getOrderStatus(), orderStatusStrategy);
        }
        ObservableList<Order> observableListOfOrders = FXCollections.observableArrayList(listOfOrders);

        // Set cell value factories for each column
        orderIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getOrderId()).asObject());
        deliveryInfoColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDeliveryInfo().getReceiver()));
        orderStatusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatusDescription()));

        // Set the items for the TableView
        orderTableView.setItems(observableListOfOrders);
    }
}
