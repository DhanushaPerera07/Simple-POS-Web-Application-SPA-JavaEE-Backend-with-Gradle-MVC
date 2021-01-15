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
 * @since : 14/01/2021
 * @since : 14/01/2021
 * @since : 14/01/2021
 **/
/**
 * @author : Dhanusha Perera
 * @since : 14/01/2021
 **/
package lk.ijse.dep.web.business;

import lk.ijse.dep.web.dao.DataAccess;
import lk.ijse.dep.web.dto.CustomerDTO;
import lk.ijse.dep.web.dto.ItemDTO;
import lk.ijse.dep.web.dto.OrderDTO;
import lk.ijse.dep.web.entity.Customer;
import lk.ijse.dep.web.entity.Item;
import lk.ijse.dep.web.entity.Order;
import lk.ijse.dep.web.entity.OrderDetail;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class AppWideBO {

    DataAccess dataAccess;

    public AppWideBO(Connection connection) {
        this.dataAccess = new DataAccess(connection);
    }

    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException {
        Customer customer = new Customer(
                customerDTO.getId(),
                customerDTO.getName(),
                customerDTO.getAddress()
        );
        return this.dataAccess.saveCustomer(customer);
    }

    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException {

        Customer customer = new Customer(
                customerDTO.getId(),
                customerDTO.getName(),
                customerDTO.getAddress()
        );

        return this.dataAccess.updateCustomer(customer);
    }

    public boolean deleteCustomer(String id) throws SQLException {
        return this.dataAccess.deleteCustomer(id);
    }

    public List<CustomerDTO> getAllCustomers() throws SQLException {
        return this.dataAccess.getAllCustomers().stream().map(c -> new CustomerDTO(
                c.getId(),
                c.getName(),
                c.getAddress()
        )).collect(Collectors.toList());
    }


    // -------------------

    public boolean saveItem(ItemDTO dto) throws Exception{
        Item item = new Item(dto.getCode(), dto.getDescription(), dto.getUnitPrice(), dto.getQtyOnHand());
        return dataAccess.saveItem(item);
    }

    public boolean updateItem(ItemDTO dto) throws Exception{
        Item item = new Item(dto.getCode(), dto.getDescription(), dto.getUnitPrice(), dto.getQtyOnHand());
        return dataAccess.updateItem(item);
    }

    public boolean deleteItem(String code) throws Exception{
        return dataAccess.deleteItem(code);
    }

    public List<ItemDTO> getAllItems() throws Exception{
        return dataAccess.getAllItems().stream().
                map(i -> new ItemDTO(i.getCode(), i.getDescription(), i.getUnitPrice(), i.getQtyOnHand()))
                .collect(Collectors.toList());
    }

    // --------------------------------------

    public boolean saveOrder(OrderDTO dto)throws Exception{
        Order order = new Order(dto.getOrderId(), Date.valueOf(dto.getOrderDate()), dto.getCustomerId());
        List<OrderDetail> orderDetails = dto.getOrderDetails().stream()
                .map(detailDto -> new OrderDetail(detailDto.getOrderId(),
                        detailDto.getItemCode(), detailDto.getQty(), detailDto.getUnitPrice()))
                .collect(Collectors.toList());
        return dataAccess.saveOrder(order, orderDetails);
    }
}
