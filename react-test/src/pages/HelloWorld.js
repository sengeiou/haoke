export default () => {
    const t = () => "pig"
    return (
        <div>hello world {t()}</div>
    );
}
/*
* umi dev
* tyarn add umi-plugin-react --dev  在config.js中引入umi-plugin-react插件
* umi build 生成dist下的index.html umi.js
*
* */