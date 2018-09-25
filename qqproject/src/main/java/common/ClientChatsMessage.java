package common;

/**
 * 客户端群聊消息
 */
public class ClientChatsMessage extends BaseMessage{
	//消息内容
	private String message ;

	private String senderAddr ;

	public int getMessageType() {
		return CLIENT_TO_SERVER_CHATS;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSenderAddr() {
		return senderAddr;
	}

	public void setSenderAddr(String senderAddr) {
		this.senderAddr = senderAddr;
	}
}
