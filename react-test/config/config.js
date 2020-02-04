export default {
    plugins:[
        ['umi-plugin-react',{
            dva: true,   //开启dva进行数据分层管理
            antd: true // 开启Ant Design功能
        }]
    ],
    routes: [{
        path: '/',
        component: '../layouts', //配置布局路由
        routes: [ // 手动配置路由，默认路径路由配置
            {
                path: '/',
                component: './index'
            },
            {
                path: '/myTabs',
                component: './myTabs'
            },
            {
                path: '/user',
                routes: [
                    {
                        path: '/user/list',
                        component: './user/UserList'
                    },
                    {
                        path: '/user/add',
                        component: './user/UserAdd'
                    }
                ]
            }
        ]
    }]
};
// umi dev