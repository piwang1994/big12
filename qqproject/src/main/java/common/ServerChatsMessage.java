package common;

import java.io.ByteArrayOutputStream;

import static util.DataUtil.intTobytes;

public class ServerChatsMessage extends BaseMessage {
    private byte[] sendAddrBytes;
    private byte[] messageBytes;


    public byte[] getSendAddrBytes(byte[] remoteAddrBytes) {
        return sendAddrBytes;
    }

    public void setSendAddrBytes(byte[] sendAddrBytes) {
        this.sendAddrBytes = sendAddrBytes;
    }

    public byte[] getMessadgeBytes() {
        return messageBytes;
    }

    public void setMessageBytes(byte[] messadgeBytes) {
        this.messageBytes = messadgeBytes;
    }

    public int getMessageType() {
     return SERVER_TO_CLIENT_CHATS;
    }

    @Override
    public byte[] popPack() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(this.getMessadgeBytes());

        baos.write(this.sendAddrBytes.length);
        baos.write(this.sendAddrBytes);

        baos.write(intTobytes(this.getMessadgeBytes().length));
        baos.write(this.getMessadgeBytes());
        return this.messageBytes;
    }
}
