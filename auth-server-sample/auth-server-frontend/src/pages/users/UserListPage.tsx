import {Table, Tag} from "antd";
import {ColumnsType} from "antd/es/table";
import {getUsers} from "../../services/UserService.ts";
import {useContext, useEffect, useState} from "react";
import {AxiosResponse} from "axios";
import {Link} from "react-router-dom";
import {AuthContext, IAuthContext} from "react-oauth2-code-pkce";

interface DataType {
    key: string;
    id: string,
    username: string;
    lastName: string;
    firstName: string;
    email: string,
    active: boolean;
}

const columns: ColumnsType<DataType> = [
    {
        title: 'Username',
        dataIndex: 'username',
        key: 'username',
    },
    {
        title: 'First name',
        dataIndex: 'firstName',
        key: 'firstName',
    },
    {
        title: 'Last name',
        dataIndex: 'lastName',
        key: 'lastName',
    },
    {
        title: 'Email',
        key: 'email',
        dataIndex: 'email',
    },
    {
        title: 'Active',
        key: 'active',
        dataIndex: 'active',
        render: (_, {active}) => (
            <>
                {
                    active
                        ? <Tag color="green" key="green">
                            Active
                        </Tag>
                        : <Tag color="red" key="red">
                            No active
                        </Tag>
                }
            </>
        ),
    },
    {
        title: 'Action',
        key: 'action',
        render: (_, record) => (
            <Link to={"/users/" + record.id}>Open</Link>
        ),
    },
];

export function UserListPage() {
    const {token} = useContext<IAuthContext>(AuthContext)
    const [loading, setLoading] = useState(true);
    const [data, setData] = useState<DataType[]>([]);

    useEffect(() => {
        console.log(token);
        getUsers(token).then(function (response: AxiosResponse<DataType[]>) {
            const users = response.data.map(e => {
                return {
                    key: e.id,
                    id: e.id,
                    username: e.username,
                    firstName: e.firstName,
                    lastName: e.lastName,
                    active: e.active,
                    email: e.email
                }
            })
            setData(users);
        })
            .catch(function (error) {
                console.log(error);
            })
            .finally(function () {
                setLoading(false);
            });
    }, [])

    return (
        <>
            <h2>Users</h2>
            <Table bordered
                   loading={loading}
                   columns={columns}
                   dataSource={data}/>
        </>
    );
}