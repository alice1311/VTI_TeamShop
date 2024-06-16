import React, { useEffect, useState } from "react";
import "./SalesManager.css";
import { Link } from "react-router-dom";
import { Breadcrumb, Button, Flex, Modal, Table, message } from "antd";
import axios from "axios";
import qs from 'qs';

const SalesManager = () => {
  const { confirm } = Modal;
  const [data, setData] = useState();
  const [messageApi, contextHolder] = message.useMessage();
  const [sales, setSales] = useState([]);
  const [loading, setLoading] = useState(false);
  const [optionVisible, setOptionVisible] = useState(false);
  const [salesSelected, setSalesSelected] = useState([]);
  const [tableParams, setTableParams] = useState({
    pagination: {
      current: 1,
      pageSize: 10,
    },
  });


  const getRandomuserParams = (params) => ({
    results: params.pagination?.pageSize,
    page: params.pagination?.current,
    ...params,
  });

  const successMessage = () => {
    messageApi.open({
      type: "success",
      content: "Xoá khuyến mãi thành công",
    });
  };

  const errorMessage = () => {
    messageApi.open({
      type: "error",
      content: "Xoá khuyến mãi thất bại",
    });
  };

  const showDeleteConfirm = () => {
    confirm({
      title: `${salesSelected?.length} khuyến mãi sẽ bị xoá vĩnh viễn`,
      content: "Tiếp tục?",
      okText: "Xoá khuyến mãi",
      okType: "danger",
      cancelText: "Huỷ",
      maskClosable: true,
      onOk() {
        handleDeleteSales();
      },
      onCancel() {},
    });
  };

  const fetchData = () => {
    setLoading(true);
    const adminData = JSON.parse(localStorage.getItem("user"));

    fetch(`https://randomuser.me/api?${qs.stringify(getRandomuserParams(tableParams))}`)
    .then((res) => res.json())
    .then(({ results }) => {
        setData(results);
        setLoading(false);
        setTableParams({
            ...tableParams,
            pagination: {
                ...tableParams.pagination,
                total: 200,
                // 200 is mock data, you should read it from server
                // total: data.totalCount,
            },
        });
    });

    axios
      .get("http://localhost:8080/api/v1/sales", {
        auth: {
          username: adminData.username,
          password: adminData.password,
        },
      })
      .then((response) => {
        const salesFormatted = response.data.map((sales) => {
          return {
            key: sales.id,
            name: { id: sales.id, name: sales.sale_info },
            percent_sale: sales.percent_sale,
            start_date: sales.start_date,
            end_date: sales.end_date,
          };
        });

        setSales(salesFormatted);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
  };

  useEffect(() => {
    window.scrollTo(0, 0);

    fetchData();
  }, [JSON.stringify(tableParams)]);

  const handleTableChange = (pagination, filters, sorter) => {
    setTableParams({
        pagination,
        filters,
        ...sorter,
    });

    // `dataSource` is useless since `pageSize` changed
    if (pagination.pageSize !== tableParams.pagination?.pageSize) {
        setData([]);
    }
};

  const columns = [
    {
      title: "Tên khuyến mãi",
      dataIndex: "sale_info",
      render: (record) => (
        <Link to={`/admin/sales/${record.id}`}>{record.sale_info}</Link>
      ),
    },
    {
      title: "Phần trăm khuyến mãi",
      dataIndex: "percent_sale",
    },
    {
      title: "Ngày bắt đầu",
      dataIndex: "start_date",
    },
    {
      title: "Ngày kết thúc",
      dataIndex: "end_date",
    },
    {
      title: <Link to={`/admin/sales/update`}>Sửa</Link>,
      dataIndex: "update_sales",
    },
  ];

  const rowSelection = {
    salesSelected,
    onChange: (selectedRowKeys) => {
      !selectedRowKeys.length
        ? setOptionVisible(false)
        : setOptionVisible(true);
      setSalesSelected(selectedRowKeys);
      console.log(selectedRowKeys.join(","));
    },
  };

  const handleDeleteSales = () => {
    setLoading(true);

    const adminData = JSON.parse(localStorage.getItem("user"));

    axios
      .delete(
        `http://localhost:8080/api/v1/sales?ids=${salesSelected.join(",")}`,
        {
          auth: {
            username: adminData.username,
            password: adminData.password,
          },
        }
      )
      .then((response) => {
        console.log("Delete product successfully");
        successMessage();
        fetchData();
        setSalesSelected([]);
        setOptionVisible(false);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
        errorMessage();
      });
  };

  const handleDeselectAll = () => {
    // setProductSelected([]);
  };

  return (
    <Flex
      className="SalesManager"
      vertical="true"
      gap={20}
      style={{ position: "relative" }}
    >
      {contextHolder}
      {/* title của page */}
      <Breadcrumb
        items={[
          {
            title: "Quản lý",
          },
          {
            title: "Quản lý khuyến mãi",
          },
        ]}
      />

      {optionVisible && (
        <Flex className="option-sticky" justify="space-between" align="center">
          <span>{`Đã chọn ${salesSelected?.length} sản phẩm`}</span>
          <Flex gap={10} justify="flex-end">
            <Button
              type="primary"
              style={{ width: "fit-content" }}
              onClick={() => handleDeselectAll()}
            >
              Bỏ chọn
            </Button>
            <Button
              type="primary"
              danger
              style={{ width: "fit-content" }}
              onClick={showDeleteConfirm}
            >
              Xoá
            </Button>
          </Flex>
        </Flex>
      )}

      {/* table */}
      <Table
        rowSelection={rowSelection}
        columns={columns}
        dataSource={sales}
        loading={loading}
        pagination={tableParams.pagination}
        onChange={handleTableChange}
        onRow={(record, rowIndex) => {
          return {
            onClick: (event) => {},
          };
        }}
        size="large"
      />
    </Flex>
  );
};

export default SalesManager;
