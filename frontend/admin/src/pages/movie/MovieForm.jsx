import { Button, Calendar, Form, Input, InputNumber, Select, Spin, Upload, notification } from 'antd';
import queryString from 'query-string';
import React, { useEffect, useState } from 'react';
import { useLocation, useParams } from 'react-router-dom';
import movieApi from '../../api/movieApi';
import categoryApi from '../../api/categoryApi';
import dayjs from 'dayjs'

const MovieForm = (props) => {
    const location = useLocation();
    const { id } = useParams()


    let now = new Date();
    let year = now.getFullYear();
    let month = String(now.getMonth() + 1).padStart(2, '0');
    let day = String(now.getDate()).padStart(2, '0');
    let currentDate = `${year}-${month}-${day}`;

    const [isLoading, setIsLoading] = useState(false)
    const [categories, setCategories] = useState([])
    const [disableStatus, setDisableStatus] = useState(false);
    const [form] = Form.useForm()
    // const [data, setData] = useState({})
    const [releaseDate, setReleaseDate] = useState(currentDate)


    // console.log(currentDate); // e.g. "2023-04-24"



    const [fileList, setFileList] = useState([])

    const handleChangeUpload = ({ fileList: newFileList }) => {
        setFileList(newFileList);
        console.log("Changeeeeeeeeeeee")
    }

    const handleSubmit = (data) => {
        setIsLoading(true)

        form.validateFields()

        data.releaseDate = data.releaseDate.format('YYYY-MM-DD')
        
        if (id) {
            const formData = new FormData()
            formData.append("movie",
                new Blob([JSON.stringify({ ...data })], { type: "application/json" }))

            if (fileList[0] && fileList[0].originFileObj) {
                formData.append("thumbnailFile", fileList[0].originFileObj)
            }

            movieApi.update(id, formData).then(res => {
                console.log(res);
                console.log("OKKKKKKKKKKKKK")
                notification.info({
                    message: `Notification`,
                    description: 'Success',
                    placement: 'top',
                });
            }).catch(err => {

            }).finally(() => {
                setIsLoading(false)
            })
        } else {
            delete data.id
            const formData = new FormData()
            formData.append("movie",
                new Blob([JSON.stringify({ ...data })], { type: "application/json" }))

            if (fileList[0] && fileList[0].originFileObj) {
                formData.append("thumbnailFile", fileList[0].originFileObj)
            }
            movieApi.create(formData).then(res => {
                console.log(res);
                notification.info({
                    message: `Notification`,
                    description: 'Success',
                    placement: 'top',
                });
            }).catch(err => {

            }).finally(() => {
                setIsLoading(false)
            })
        }

    }

    useEffect(() => {
        form.resetFields()

        categoryApi.getAll().then(res => {
            console.log(res)
            setCategories(res.map(i => ({ ...i, label: i.name, value: i.id })))
        })

        if (id) {
            movieApi.getById(id).then(res => {
                console.log(res);
                console.log(typeof res.releaseDate)
                // setData(res)
                form.setFields([
                    {
                        name: "id",
                        value: res.id
                    },
                    {
                        name: "name",
                        value: res.name
                    },
                    {
                        name: "duration",
                        value: res.duration
                    },
                    {
                        name: "description",
                        value: res.description
                    },
                    {
                        name: "categories",
                        value: res.categories.map(i => i.id)
                    },
                    // {
                    //     name: "thumbnail",
                    //     value: res.thumbnail
                    // },
                    {
                        name: "releaseDate",
                        value: dayjs(res.releaseDate, 'YYYY/MM/DD')
                    }
                ])
                setFileList([{
                    uid: "-1",
                    name: "xxx.png",
                    status: "done",
                    thumbUrl: res.thumbnailUrl
                }])
            }).catch(err => {
            })

        } else {

        }

    }, [location.pathname])


    return (
        <Spin spinning={isLoading}>
            <Form
                name="form"
                form={form}
                validateTrigger="onSubmit"
                labelCol={{ flex: '110px' }}
                labelAlign="left"
                labelWrap
                wrapperCol={{ flex: 1 }}
                autoComplete="off"
                onFinish={handleSubmit}
                requiredMark={false}
            >
                <Form.Item
                    label="ID"
                    name="id"
                >
                    <Input placeholder="ID" disabled size='large' />
                </Form.Item>
                <Form.Item
                    label="Name"
                    name="name"
                    rules={[
                        {
                            required: true,
                            message: "Name cannot be empty"
                        }
                    ]}
                >
                    <Input placeholder="Name" size='large' />
                </Form.Item>
                <Form.Item
                    label="Duration"
                    name="duration"
                    rules={[
                        {
                            required: true,
                            message: "Duration cannot be empty"
                        }
                    ]}
                >
                    <InputNumber placeholder="Duration" style={{ width: '100%' }} size='large' />
                </Form.Item>

                <Form.Item
                    label="Release Date"
                    name="releaseDate"
                    rules={[
                        {
                            required: true,
                            message: "Release Date cannot be empty"
                        }
                    ]}
                >
                    <Calendar style={{ width: 300 }} fullscreen={false} onChange={(value) => setReleaseDate(value.format('YYYY-MM-DD'))} />
                </Form.Item>

                <Form.Item
                    label="Description"
                    name="description"
                    rules={[
                        {
                            required: true,
                            message: "Description cannot be empty"
                        }
                    ]}
                >
                    <Input.TextArea
                        // value={value}
                        // onChange={(e) => setValue(e.target.value)}
                        placeholder="Description"
                        autoSize={{
                            minRows: 4,
                            maxRows: 8,
                        }}
                        size='large'
                    />
                </Form.Item>
                <Form.Item
                    label="Thumbnail"
                    name="thumbnail"
                    style={{ display: 'none' }}
                >
                    <Input size='large'
                    />
                </Form.Item>
                <Form.Item
                    label="Category"
                    name="categories"
                    rules={[
                        {
                            required: true,
                            message: "Category cannot be empty"
                        }
                    ]}
                >
                    <Select
                        mode="multiple"
                        style={{
                            width: '100%',
                        }}
                        size='large'
                        placeholder="Select categories"
                        // defaultValue={['china']}
                        // onChange={(e) => console.log(e.target.value)}
                        optionLabelProp="label"
                        filterOption={(input, option) => {

                            return (option?.label ?? '').includes(input)
                        }}
                        filterSort={(optionA, optionB) =>
                            (optionA?.label ?? '').toLowerCase().localeCompare((optionB?.label ?? '').toLowerCase())
                        }
                        // loading={true}
                        options={categories}
                    >

                    </Select>
                </Form.Item>
                <Form.Item
                    label="Thumbnail"
                    name="thumbnailFile"
                    rules={[
                        {
                            validator(_, value) {
                                const id = form.getFieldValue('id')
                                if (!id && fileList.length === 0) {
                                    return Promise.reject('Chưa upload ảnh');
                                }
                                return Promise.resolve()
                            }
                        }
                    ]}
                >
                    <Upload
                        listType="picture-card"
                        fileList={fileList}
                        onChange={handleChangeUpload}
                        multiple={false}
                    
                        beforeUpload={() => false}
                        showUploadList={{
                            showPreviewIcon: false
                        }}
                        accept="image/*"
                    >
                        {fileList.length < 1 && '+ Upload'}
                    </Upload>
                </Form.Item>

                <Form.Item>
                    <Button size='large' type="primary" htmlType="submit" disabled={isLoading} loading={isLoading}>
                        Save
                    </Button>
                </Form.Item>
            </Form>
        </Spin>
    )
}

export default MovieForm