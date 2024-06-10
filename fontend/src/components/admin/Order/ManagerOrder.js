
import "./ManagerOrder.css"
import { useEffect, useState } from "react";
import axios from "axios";
import { Badge, Breadcrumb, Flex, Table, Button, message, Modal } from "antd";
import { Link } from "react-router-dom";
import React from 'react';
import { format } from 'date-fns';

function ManagerOrder() {

    const { confirm } = Modal;
    const [messageApi, contextHolder] = message.useMessage();
    const [orders, setOrders] = useState([]);
    const [loading, setLoading] = useState(false);
    const [optionVisible, setOptionVisible] = useState(false);
    const adminData = JSON.parse(localStorage.getItem('user'));
    const fetchData = () => {
        setLoading(true);
        axios.get('http://localhost:8080/api/v1/orders/getAll',{
            auth: {
                username: adminData.username,
                password: adminData.password
            }
        })
        
            .then(response => {
                const OrdersFormatted = response.data.map((order) => {
                    return ({
                        order_id:order.id,
                        total_amount: order. total_amount,
                        oder_date: order.oder_date,
                        oderStatus: order. oderStatus,
                        customer_name: order.customer_name,
                        employee_name: order.employee_name,
                        address: order.address,
                        phone: order.phone,
                        payment_method: order.payment_method
                    })
                })

                setOrders(OrdersFormatted);
                setLoading(false);
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });

       
    }

    useEffect(() => {

        window.scrollTo(0, 0);

        fetchData();

    }, [])

    const columns = [
        {
            title: 'ID',
            dataIndex: 'order_id',
            render: (record) => <Link to={`/admin/orders/checkOrder/${record}`}>{record}</Link>,
        },
        {
            title: 'Tổng tiền',
            dataIndex: 'total_amount',
           
        },
        {
            title: 'Ngày đặt',
            dataIndex: 'oder_date',
        },
        {
            title: 'Trạng thái',
            dataIndex: 'oderStatus',
           
        },
        {
            title: 'Khách hàng',
            dataIndex: 'customer_name',
           
        },
        {
            title: 'Nhân viên',
            dataIndex: 'employee_name',
            
        },
        {
            title: 'Địa chỉ',
            dataIndex: 'address',
          
        },
        {
            title: 'SDT',
            dataIndex: 'phone',
          
        },
        {
            title: 'Phương thức',
            dataIndex: 'payment_method',
           
        }
    ];

    return (
        <Flex className="ProductManager" vertical="true" gap={20} style={{ position: "relative" }}>
            {contextHolder}
            <Breadcrumb
                items={[
                    {
                        title: 'Quản lý',
                    },
                    {
                        title: 'Quản lý hóa đơn',
                    },
                ]}
            />

            {
                optionVisible
                
            }
            <Table
                columns={columns}
                dataSource={orders}
                loading={loading}
                pagination={true}
                onRow={(record, rowIndex) => {
                    return {
                        onClick: (event) => { }
                    };
                }}
                size="large"
            />
        </Flex>
    )
}

export default ManagerOrder;