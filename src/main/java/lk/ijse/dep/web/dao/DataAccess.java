/**
 * MIT License
 * <p>
 * Copyright (c) 2020 Dhanusha Perera
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * @author : Dhanusha Perera
 * @author : Dhanusha Perera
 * @author : Dhanusha Perera
 * @author : Dhanusha Perera
 * @author : Dhanusha Perera
 * @author : Dhanusha Perera
 * @author : Dhanusha Perera
 * @author : Dhanusha Perera
 * @author : Dhanusha Perera
 * @author : Dhanusha Perera
 * @since : 14/01/2021
 * @since : 14/01/2021
 * @since : 14/01/2021
 * @since : 14/01/2021
 * @since : 14/01/2021
 * @since : 14/01/2021
 * @since : 14/01/2021
 * @since : 14/01/2021
 * @since : 14/01/2021
 * @since : 14/01/2021
 **/
/**
 * @author : Dhanusha Perera
 * @since : 14/01/2021
 **/
package lk.ijse.dep.web.dao;

import lk.ijse.dep.web.entity.Customer;
import lk.ijse.dep.web.entity.Item;
import lk.ijse.dep.web.entity.Order;
import lk.ijse.dep.web.entity.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataAccess {

    Connection connection;

    public DataAccess(Connection connection) {
        this.connection = connection;
    }

    //--------------------------------------------------------------
    // CUSTOMER
    //--------------------------------------------------------------

    // customer add
    public boolean saveCustomer(Customer customer) throws SQLException {
        /* INSERT PART */
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO `customer` (`name`,`address`) VALUES (?,?);");
        pstm.setObject(1, customer.getName());
        pstm.setObject(2, customer.getAddress());

        /* return success */
        return (pstm.executeUpdate() > 0);
    }

    // customer update
    public boolean updateCustomer(Customer customer) throws SQLException {


        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE `customer` SET `name`=?,`address`=? WHERE id=?"
        );
        preparedStatement.setObject(1, customer.getName());
        preparedStatement.setObject(2, customer.getAddress());
        preparedStatement.setObject(3, customer.getId());

        return preparedStatement.executeUpdate() > 0;
    }

    // customer delete
    public boolean deleteCustomer(String id) throws SQLException {

        PreparedStatement preparedStatement2 = connection.prepareStatement(
                "DELETE `customer`WHERE id=?"
        );

        preparedStatement2.setObject(1, id);

        return preparedStatement2.executeUpdate() > 0;
    }

    // list customer
    public List<Customer> getAllCustomers() throws SQLException {

        PreparedStatement pstm = connection.
                prepareStatement("SELECT * FROM `customer`;");

        /* execute the query and get the result set */
        ResultSet resultSet = pstm.executeQuery();

        /* Create itemDTO List to store if there is/are Item(s).
         * add those items to the arrayList */
        List<Customer> customerList = new ArrayList<>();

        /* Go through with the result set and
         * add item to item to itemList(array) */
        while (resultSet.next()) {
            customerList.add(
                    new Customer(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3)
                    ));
        }


        return customerList;
    }

    // get customer
    public Customer getCustomer(String id) throws SQLException {

        PreparedStatement pstm = connection.
                prepareStatement("SELECT * FROM `customer` WHERE `id`=?;");
        pstm.setObject(1, id);

        /* execute the query and get the result set */
        ResultSet resultSet = pstm.executeQuery();

        Customer customer = null;

        /* Go through with the result set and
         * add item to item to itemList(array) */
        while (resultSet.next()) {
            customer = new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            );
        }

        return (customer == null) ? new Customer() : customer;
    }


    // --------------------------------------------------------


    //--------------------------------------------------------------
    // ITEM
    //--------------------------------------------------------------

    // item add
    public boolean saveItem(Item item) throws SQLException {
        /* INSERT PART */
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO `item` (`description`,`unit_price`,`qty_on_hand`) VALUES (?,?,?);");
        pstm.setObject(1, item.getDescription());
        pstm.setObject(2, item.getUnitPrice());
        pstm.setObject(3, item.getQtyOnHand());

        /* return success */
        return (pstm.executeUpdate() > 0);
    }

    // item update
    public boolean updateItem(Item item) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE `item` SET `description`=?,`unit_price`=?,`qty_on_hand`=? WHERE code=?"
        );
        preparedStatement.setObject(1, item.getDescription());
        preparedStatement.setObject(2, item.getUnitPrice());
        preparedStatement.setObject(3, item.getQtyOnHand());
        preparedStatement.setObject(4, item.getCode());
        preparedStatement.setObject(3, item.getDescription());

        return preparedStatement.executeUpdate() > 0;
    }

    // item delete
    public boolean deleteItem(String id) throws SQLException {
        PreparedStatement preparedStatement2 = connection.prepareStatement(
                "DELETE `item`WHERE id=?"
        );

        preparedStatement2.setObject(1, id);

        return preparedStatement2.executeUpdate() > 0;
    }

    // list item
    public List<Item> getAllItems() throws SQLException {
        PreparedStatement pstm = connection.
                prepareStatement("SELECT * FROM `item`;");

        /* execute the query and get the result set */
        ResultSet resultSet = pstm.executeQuery();

        /* Create itemDTO List to store if there is/are Item(s).
         * add those items to the arrayList */
        List<Item> itemList = new ArrayList<>();

        /* Go through with the result set and
         * add item to item to itemList(array) */
        while (resultSet.next()) {
            itemList.add(
                    new Item(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getBigDecimal(3),
                            resultSet.getInt(4)
                    ));
        }


        return itemList;
    }

    // get item
    public Item getItem(String id) throws SQLException {
        PreparedStatement pstm = connection.
                prepareStatement("SELECT * FROM `item` WHERE code=?;");

        /* execute the query and get the result set */
        ResultSet resultSet = pstm.executeQuery();

        /* Create itemDTO List to store if there is/are Item(s).
         * add those items to the arrayList */
        Item item = null;

        /* Go through with the result set and
         * add item to item to itemList(array) */
        while (resultSet.next()) {
            item = new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getBigDecimal(3),
                    resultSet.getInt(4)
            );
        }

        return (item == null) ? new Item() : item;
    }

    // --------------------------------------------------------------

    //--------------------------------------------------------------
    // ORDER
    //--------------------------------------------------------------

    // Order add
    public boolean saveOrder(Order order, List<OrderDetail> orderDetailsList) throws Exception {
        try {
            /* Let's start transactions */
            connection.setAutoCommit(false);

            PreparedStatement pstm = connection.prepareStatement("INSERT INTO `order` VALUES (?,?,?)");
            pstm.setString(1, order.getId());
            pstm.setDate(2, order.getDate());
            pstm.setString(3, order.getCustomerId());

            if (pstm.executeUpdate() > 0) {
                pstm = connection.prepareStatement("INSERT INTO order_detail VALUES (?,?,?,?)");
                PreparedStatement pstm2 = connection.
                        prepareStatement("UPDATE item SET qty_on_hand = qty_on_hand - ? WHERE code=?");

                for (OrderDetail orderDetail : orderDetailsList) {
                    pstm.setString(1, order.getId());
                    pstm.setString(2, orderDetail.getItemCode());
                    pstm.setInt(3, orderDetail.getQty());
                    pstm.setBigDecimal(4, orderDetail.getUnitPrice());

                    pstm2.setInt(1, orderDetail.getQty());
                    pstm2.setString(2, orderDetail.getItemCode());

                    if (pstm.executeUpdate() == 0 || pstm2.executeUpdate() == 0) {
                        throw new RuntimeException("Failed to complete the transaction");
                    }
                }

                connection.commit();
                return true;
            } else {
                throw new RuntimeException("Failed to complete the transaction");
            }
        } catch (Throwable t) {
            connection.rollback();
            throw t;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    // Order update
    // TODO: updateOrder method should be corrected
//    public boolean updateOrder(Order order, List<OrderDetail> orderDetailsList) {
//        try {
//            /* Let's start transactions */
//            connection.setAutoCommit(false);
//
//            PreparedStatement pstm = connection.prepareStatement(
//                    "UPDATE `order` SET date=?,customer_id=? WHERE id=?;");
//            pstm.setDate(1, order.getDate());
//            pstm.setString(2, order.getCustomerId());
//            pstm.setString(3, order.getId());
//
//            if (pstm.executeUpdate() > 0) {
//                pstm = connection.prepareStatement("UPDATE `order_detail` SET `item_code`=?,`qty`=?,`unit_price`=? WHERE `order_id`=?");
//                PreparedStatement pstm2 = connection.
//                        prepareStatement("UPDATE item SET qty_on_hand = qty_on_hand - ? WHERE code=?");
//
//                for (OrderDetail orderDetail : orderDetailsList) {
//                    pstm.setString(1, order.getId());
//                    pstm.setString(2, orderDetail.getItemCode());
//                    pstm.setInt(3, orderDetail.getQty());
//                    pstm.setBigDecimal(4, orderDetail.getUnitPrice());
//
//                    pstm2.setInt(1, orderDetail.getQty());
//                    pstm2.setString(2, orderDetail.getItemCode());
//
//                    if (pstm.executeUpdate() == 0 || pstm2.executeUpdate() == 0) {
//                        throw new RuntimeException("Failed to complete the transaction");
//                    }
//                }
//
//                connection.commit();
//                return true;
//            } else {
//                throw new RuntimeException("Failed to complete the transaction");
//            }
//        } catch (Throwable t) {
//            connection.rollback();
//            throw t;
//        } finally {
//            connection.setAutoCommit(true);
//        }
//
//        return true; //
//    }

    // Order delete
/*    public boolean deleteOrder(String id) {
        return true;
    }

    // list Order
    public List<Order> getAllOrder(String id) {
        return new ArrayList<Order>();
    }

    // get Order
    public Order getOrder(String id) {
        return new Order();
    }*/


    // --------------------------------------------------------------

}
