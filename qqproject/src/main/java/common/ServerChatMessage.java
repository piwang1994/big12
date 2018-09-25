package common;

import java.io.ByteArrayOutputStream;

import static util.DataUtil.intTobytes;

/**
 * 服务器私聊
 */
public class ServerChatMessage extends BaseMessage {

	private byte[] sendAddrBytes;
	private byte[] recvAddrBytes;
	private byte[] messageBytes;

	public byte[] getSendAddrBytes(byte[] remoteAddrBytes) {
		return sendAddrBytes;
	}

	public void setSendAddrBytes(byte[] sendAddrBytes) {
		this.sendAddrBytes = sendAddrBytes;
	}

	public byte[] getRecvAddrBytes() {
		return recvAddrBytes;
	}

	public void setRecvAddrBytes(byte[] recvAddrBytes) {
		this.recvAddrBytes = recvAddrBytes;
	}

	public byte[] getMessageBytes() {
		return messageBytes;
	}

	public void setMessageBytes(byte[] messageBytes) {
		this.messageBytes = messageBytes;
	}

	@Override
	public int getMessageType() {
		return SERVER_TO_CLIENT_CHAT;
	}


	public byte[] popPack() throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(this.getMessageType());

		baos.write(this.sendAddrBytes.length);
		baos.write(this.sendAddrBytes);

		baos.write(intTobytes(this.messageBytes.length));
		baos.write(this.messageBytes);

		return this.messageBytes;
	}
}