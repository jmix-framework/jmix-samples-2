import {Checkbox, Form, Input, Spin} from "antd";
import {useForm} from "antd/es/form/Form";
import {User, getUser} from "../../services/UserService.ts";
import {useParams} from "react-router";
import {useContext, useEffect, useState} from "react";
import {AxiosResponse} from "axios";
import {AuthContext, IAuthContext} from "react-oauth2-code-pkce";

export function UserPage() {
    const [form] = useForm();
    const {token} = useContext<IAuthContext>(AuthContext)
    const [loading, setLoading] = useState(true);

    const {subscriptionId} = useParams<{ subscriptionId: string }>();

    useEffect(() => {
        getUser(subscriptionId, token).then(function (response: AxiosResponse<User>) {
            form.setFieldsValue({
                id: response.data.id,
                username: response.data.username,
                firstName: response.data.firstName,
                lastName: response.data.lastName,
                active: response.data.active,
                email: response.data.email
            });
        })
            .catch(function (error) {
                console.log(error);
            })
            .finally(function () {
                setLoading(false);
            });
    }, [subscriptionId, token, form])

    return (
        <Spin spinning={loading}>
            <Form disabled
                  layout="vertical"
                  style={{maxWidth: 600}}
                  form={form}>
                <Form.Item label="Username"
                           name="username">
                    <Input/>
                </Form.Item>
                <Form.Item label="Email"
                           name="email">
                    <Input/>
                </Form.Item>
                <Form.Item label="First name"
                           name="firstName">
                    <Input/>
                </Form.Item>
                <Form.Item label="Last name"
                           name="lastName">
                    <Input/>
                </Form.Item>
                <Form.Item name="active"
                           valuePropName="checked">
                    <Checkbox>Active</Checkbox>
                </Form.Item>
            </Form>
        </Spin>
    );
}