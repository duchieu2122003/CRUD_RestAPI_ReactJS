import {
  Form,
  Input,
  Select,
  Radio,
  Button,
  Table,
  Space,
  Pagination,
} from "antd";
import { useState, useEffect } from "react";
import axios from "axios";
import { urlNV, urlCV } from "../config/Api";
import React from "react";

const Home = () => {
  const [ma, setMa] = useState("");
  const [ten, setTen] = useState("");
  const [gioiTinh, setGioiTinh] = useState("Nam");
  const [diaChi, setDiaChi] = useState("vietnam");
  const [sdt, setSdt] = useState("");
  const [trangThai, setTrangThai] = useState(0);
  const [idChucVu, setIdChucVu] = useState("");
  const [listChucVu, setListChucVu] = useState([]);
  const [listNhanVien, setListNhanVien] = useState([]);
  const [pageNo, setPageNo] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [check, setCheck] = useState(false);
  const [gioiTinhSearch, setGioiTinhSearch] = useState("Nam");
  const [searchValue, setSearchValue] = useState("");
  const [checkSearch, setCheckSearch] = useState(false);

  useEffect(() => {
    if (checkSearch === false) {
      loadNhanVien(pageNo);
    } else {
      handleSeach();
    }
  }, [pageNo]);

  useEffect(() => {
    loadSelectChucVu();
    loadNhanVien(pageNo);
  }, []);

  const loadNhanVien = (pageNo) => {
    setCheckSearch(false);
    axios
      .get(urlNV + "/hien-thi?pageNo=" + pageNo)
      .then((respone) => {
        setListNhanVien(respone.data.content);
        setPageNo(respone.data.number);
        setTotalPages(respone.data.totalPages);
      })
      .catch(() => {});
  };
  const loadSelectChucVu = () => {
    axios.get(urlCV + "/hien-thi").then((respone) => {
      setListChucVu(respone.data);
      setIdChucVu(respone.data[0].id);
    });
  };

  const handleAdd = () => {
    let objNew = {
      ten: ten,
      ma: ma,
      gioiTinh: gioiTinh,
      diaChi: diaChi,
      sdt: sdt,
      idChucVu: idChucVu,
      trangThai: trangThai,
    };
    axios
      .post(urlNV + "/add", objNew)
      .then((respone) => {
        console.log(respone.data);
        let request = respone.data;
        let objectAdd = {
          idNhanVien: request.idNhanVien,
          maNhanVien: request.maNhanVien,
          tenNhanVien: request.tenNhanVien,
          gioiTinh: request.gioiTinh,
          diaChi: request.diaChi,
          sdt: request.sdt,
          idChucVu: request.idChucVu,
          tenChucVu: request.tenChucVu,
          trangThai: request.trangThai,
        };
        setListNhanVien([objectAdd, ...listNhanVien]);
        alert("add ok");
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const handleDetail = (id) => {
    axios
      .get(urlNV + "/detail/" + id)
      .then((respone) => {
        let obj = respone.data;
        setMa(obj.ma);
        setTen(obj.ten);
        setGioiTinh(obj.gioiTinh);
        setDiaChi(obj.diaChi);
        setSdt(obj.sdt);
        setTrangThai(obj.trangThai);
        setIdChucVu(obj.chucVu.id);
        setCheck(true);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const handleDelete = (id) => {
    axios
      .delete(urlNV + "/delete/" + id)
      .then((respone) => {
        let listNew = listNhanVien.filter((e) => e.idNhanVien !== id);
        setListNhanVien(listNew);
        alert("Remove ok");
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const handleUpdate = (id) => {
    if (check === false) {
      return alert("Chọn detail để update");
    } else {
      setCheck(false);
      let objNew = {
        ten: ten,
        ma: ma,
        gioiTinh: gioiTinh,
        diaChi: diaChi,
        sdt: sdt,
        idChucVu: idChucVu,
        trangThai: trangThai,
      };
      axios
        .put(urlNV + "/update/" + id, objNew)
        .then((respone) => {
          let request = respone.data;
          let objectUpdate = {
            idNhanVien: request.idNhanVien,
            maNhanVien: request.maNhanVien,
            tenNhanVien: request.tenNhanVien,
            gioiTinh: request.gioiTinh,
            diaChi: request.diaChi,
            sdt: request.sdt,
            idChucVu: request.idChucVu,
            tenChucVu: request.tenChucVu,
            trangThai: request.trangThai,
          };
          let update = listNhanVien.map((record) => {
            if (record.idNhanVien === id) {
              return { ...record, ...objectUpdate };
            }
            return record;
          });
          setListNhanVien(update);
          alert("Update ok");
        })
        .catch((e) => {
          alert("Update faild");
        });
    }
  };

  const handleSeach = () => {
    setCheckSearch(true);
    let url = `${urlNV}/search?gioiTinhSearch=${gioiTinhSearch}&diaChiSdtSearch=${searchValue}&pageNo=${pageNo}`;
    axios.get(url).then((res) => {
      setListNhanVien(res.data.content);
      setPageNo(res.data.number);
      setTotalPages(res.data.totalPages);
      alert("Search ok");
    });
  };

  const renderTrangThai = (trangThai) => {
    return trangThai === 0 ? "Hoạt động" : "Ngừng hoạt động";
  };

  const renderSdt = (sdt) => {
    return sdt === null ? "Không có" : sdt;
  };
  const columns = [
    // {
    //   title: "Id",
    //   dataIndex: "idNhanVien",
    //   key: "idNhanVien",
    // },
    {
      title: "Ma",
      dataIndex: "maNhanVien",
      key: "maNhanVien",
    },
    {
      title: "Tên NV",
      dataIndex: "tenNhanVien",
      key: "tenNhanVien",
    },

    {
      title: "Giới tính",
      dataIndex: "gioiTinh",
      key: "gioiTinh",
    },
    {
      title: "Địa chỉ",
      dataIndex: "diaChi",
      key: "diaChi",
    },
    {
      title: "Sđt",
      dataIndex: "sdt",
      key: "sdt",
    },
    {
      title: "Chức vụ",
      dataIndex: "tenChucVu",
      key: "tenChucVu",
      render: renderSdt,
    },
    {
      title: "Trang thai",
      dataIndex: "trangThai",
      key: "trangThai",
      render: renderTrangThai,
    },
    {
      title: "Hành động",
      key: "actions",
      render: (text, record) => (
        <Space>
          <Button
            key="detail"
            onClick={() => {
              handleDetail(record.idNhanVien);
            }}
            type="primary"
            htmlType="submit"
          >
            Detail
          </Button>
          <Button
            key="update"
            onClick={() => {
              handleUpdate(record.idNhanVien);
            }}
            type="primary"
            htmlType="submit"
          >
            Update
          </Button>
          <Button
            key="delete"
            onClick={() => {
              handleDelete(record.idNhanVien);
            }}
            type="primary"
            htmlType="submit"
          >
            Delete
          </Button>
        </Space>
      ),
    },
  ];

  const onFinish = (values) => {
    // console.log("Form values:", values);
  };

  return (
    <div className="test">
      <Form onFinish={onFinish}>
        <Form.Item label="Giới tính">
          <Radio.Group
            value={gioiTinhSearch}
            onChange={(e) => setGioiTinhSearch(e.target.value)}
          >
            <Radio value="Nam">Nam</Radio>
            <Radio value="Nữ">Nữ</Radio>
          </Radio.Group>
        </Form.Item>
        <Form.Item>
          <Input
            placeholder="Nhập dia chi or sdt"
            value={searchValue}
            onChange={(e) => setSearchValue(e.target.value)}
          />
        </Form.Item>
        <Form.Item>
          <Button type="primary" htmlType="submit" onClick={handleSeach}>
            Tìm kiếm
          </Button>
        </Form.Item>
        <hr></hr>
        <Form.Item label="Tên">
          <Input value={ten} onChange={(e) => setTen(e.target.value)} />
        </Form.Item>
        <Form.Item label="Ma">
          <Input value={ma} onChange={(e) => setMa(e.target.value)} />
        </Form.Item>
        <Form.Item label="Giới tính">
          <Radio.Group
            value={gioiTinh}
            onChange={(e) => setGioiTinh(e.target.value)}
          >
            <Radio value="Nam">Nam</Radio>
            <Radio value="Nữ">Nữ</Radio>
          </Radio.Group>
        </Form.Item>
        <Form.Item label="Địa chỉ">
          <Select value={diaChi} onChange={(e) => setDiaChi(e)}>
            <Select.Option value="vietnam">Việt Nam</Select.Option>
            <Select.Option value="usa">USA</Select.Option>
            <Select.Option value="japan">Japan</Select.Option>
          </Select>
        </Form.Item>
        <Form.Item>
          <Form.Item label="SĐT">
            <Input value={sdt} onChange={(e) => setSdt(e.target.value)} />
          </Form.Item>
          <Form.Item label="Chức vụ">
            <Select
              value={idChucVu}
              onChange={(e) => {
                setIdChucVu(e);
              }}
            >
              {listChucVu.map((chucVu, index) => (
                <Select.Option key={index} value={chucVu.id}>
                  {chucVu.ten}
                </Select.Option>
              ))}
            </Select>
          </Form.Item>
          <Form.Item label="Trạng thái">
            <Radio.Group
              value={trangThai}
              onChange={(e) => setTrangThai(e.target.value)}
            >
              <Radio value={0}>Hoạt động</Radio>
              <Radio value={1}>Ngừng hoạt động</Radio>
            </Radio.Group>
          </Form.Item>

          <Button type="primary" htmlType="submit" onClick={handleAdd}>
            Add
          </Button>
        </Form.Item>
        <Table
          dataSource={listNhanVien}
          columns={columns}
          rowKey="ma"
          pagination={false}
        />
        ;
        <Pagination
          simple
          current={pageNo + 1}
          onChange={(value) => {
            setPageNo(value - 1);
          }}
          total={totalPages * 10}
        />
      </Form>
    </div>
  );
};

export default Home;
