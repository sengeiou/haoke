import React from 'react';
import { Layout, Menu, Icon } from 'antd';
import Link from 'umi/link';
const { Header, Footer, Sider, Content } = Layout;
const SubMenu = Menu.SubMenu;
// layouts/index.js文件将被作为全局的布局文件。
class BasicLayout extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            collapsed: true,
        }
    }
    render(){
        return (
            <Layout>
                <Sider width={256} style={{minHeight: '100vh', color: 'white'}}>
                    <div style={{ height: '32px', background: 'rgba(255,255,255,.2)', margin: '16px'}}/>
                    <Menu
                        defaultSelectedKeys={['1']}
                        defaultOpenKeys={['sub1']}
                        mode="inline"
                        theme="dark"
                        inlineCollapsed={this.state.collapsed}
                    >
                        <SubMenu key="sub1" title={<span><Icon type="user"/><span>用户管理</span></span>}>
                            <Menu.Item key="1"><Link to="/user/add">新增用户</Link></Menu.Item>
                            <Menu.Item key="2"><Link to="/user/list">新增列表</Link></Menu.Item>
                        </SubMenu>
                    </Menu>
                </Sider>
                <Layout>
                    <Header style={{ background: '#fff', textAlign: 'center', padding: 0 }}>Header</Header>
                    <Content style={{ margin: '24px 16px 0' }}>
                        <div style={{ padding: 24, background: '#fff', minHeight: 360 }}>
                            { this.props.children }
                        </div>
                    </Content>
                    <Footer style={{ textAlign: 'center' }}>后台系统</Footer>
                </Layout>
            </Layout>
        )
    }
}
export default BasicLayout;
// this.props.children将其他页面内容作为子页面嵌入Content