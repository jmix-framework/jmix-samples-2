import {Layout, Menu} from "antd";
import Logo from "../../assets/logo/react.svg";
import {Link} from "react-router-dom";
import {LogoutOutlined} from "@ant-design/icons";
import {useContext} from "react";
import {AuthContext, IAuthContext} from "react-oauth2-code-pkce";
import {useLocation} from "react-router";

import {RouteUtils} from "../../utils/RouteUtils.ts";

import "./DemoHeader.css";

const {Header} = Layout;

export function DemoHeader() {
    const {logOut} = useContext<IAuthContext>(AuthContext);

    const {pathname} = useLocation();
    const currentManu = RouteUtils.getBreadcrumb(pathname);

    return (
        <Header
            style={{
                position: 'sticky',
                top: 0,
                zIndex: 1,
                width: '100%',
                display: 'flex',
                alignItems: 'center',
            }}
        >
            <img src={Logo} alt="Logo" className="demo-header__logo"/>
            <Menu
                theme="light"
                mode="horizontal"
                defaultSelectedKeys={[currentManu.id]}
                items={[
                    {
                        key: "home",
                        label: <Link to="/home">Home</Link>,
                    },
                    {
                        key: "users",
                        label: <Link to="/users">Users</Link>,
                    }
                ]}
            />
            <div className="demo-header__logout">
                <LogoutOutlined className="demo-header__logout-logo" onClick={() => {
                    logOut();
                }}/>
            </div>
        </Header>
    );
}