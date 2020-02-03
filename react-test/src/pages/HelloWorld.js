import React from 'react'
class HelloWorld extends React.Component{
    render() {
        // this.props.name接受属性，this.props.children接受标签内容
        return <div>hello name={this.props.name},say={this.props.children}</div>
    }
}
export default HelloWorld;
/*
* umi dev
* tyarn add umi-plugin-react --dev  在config.js中引入umi-plugin-react插件
* umi build 生成dist下的index.html umi.js
*
* */