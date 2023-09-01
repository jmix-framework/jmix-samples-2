import {JmixRoute} from "../../route/JmixRoute.tsx";
import {Breadcrumb, theme} from "antd";
import {Link} from "react-router-dom";
import {Content} from "antd/es/layout/layout";
import {HomeOutlined} from "@ant-design/icons";
import {useLocation} from "react-router";
import {RouteUtils} from "../../utils/RouteUtils.ts";

export function JmixContent() {
    const {
        token: {colorBgContainer},
    } = theme.useToken();

    const {pathname} = useLocation();
    const breadcrumb = RouteUtils.getBreadcrumb(pathname);

    return (
        <Content className="site-layout" style={{padding: '0 50px', height: '100%'}}>
            <Breadcrumb style={{margin: '16px 0'}}
                        items={[
                            {
                                title: <HomeOutlined/>,
                            },
                            {
                                title: <Link to={breadcrumb.path}>{breadcrumb.name}</Link>,
                            }
                        ]}>
            </Breadcrumb>
            <div style={{padding: 24, minHeight: 380, height: '100%', background: colorBgContainer}}>
                <JmixRoute/>
            </div>
        </Content>
    );
}