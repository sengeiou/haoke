# 发送消息
http://www.websocket-test.com/
ws://127.0.0.1:18081/ws/1001
http://www.websocket-test.com/
ws://127.0.0.1:18081/ws/1002
{
"toId":1001,
"msg":"嘿嘿"
}
# 查看历史记录
http://127.0.0.1:18081/message?fromId=1001&toId=1002
# 查看所有用户
http://127.0.0.1:18081/user?fromId=1001