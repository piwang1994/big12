package common;

/**
 * 客户端私聊消息
 */
public class ClientChatMessage extends BaseMessage{
	//消息内容
	private String message ;
	//接受地址
	private String recvAddr ;
	//发送者地址
	private String senderAddr ;

	public int getMessageType() {
		return CLIENT_TO_SERVER_CHAT;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRecvAddr() {
		return recvAddr;
	}

	public void setRecvAddr(String recvAddr) {
		this.recvAddr = recvAddr;
	}

	public String getSenderAddr() {
		return senderAddr;
	}

	public void setSenderAddr(String senderAddr) {
		this.senderAddr = senderAddr;
	}
}
