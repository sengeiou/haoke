import React from 'react';


class  List extends React.Component{

    constructor(props){
        super(props);
        this.state = {
            dataList : [1,2,3],
            maxNum : 3
        };
    }

    // 每个组件都有一个状态，保存在this.state中，状态值发生变化，React框架会自动调用render方法重新渲染
    /*this.state值在构造参数中完成，要修改this.state的值，需要调用this.setState()完成*/
    render(){
        return (
            <div>
                <ul>
                    {
                        this.state.dataList.map((value,index)=>{
                            return <li key={index}>{value}</li>
                        })
                    }
                </ul>
                <button onClick={() => {
                    let maxNum = this.state.maxNum + 1;
                    let list = [...this.state.dataList,maxNum];
                    this.setState({
                        dataList: list,
                        maxNum: maxNum
                    });
                }}>点我</button>
            </div>
        );
    }

}

export default List;