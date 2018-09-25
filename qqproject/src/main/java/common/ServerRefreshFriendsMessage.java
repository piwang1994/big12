package common;

import server.QQserver;

import java.io.ByteArrayOutputStream;

import static util.DataUtil.intTobytes;

public class ServerRefreshFriendsMessage extends BaseMessage {

    private byte[] friendsBytes;

    public byte[] getFriendsBytes(byte[] friendsBytes) {
        return this.friendsBytes;
    }

    public void setFriendsBytes(byte[] friendsBytes) {
        this.friendsBytes = friendsBytes;
    }

    @Override
    public int getMessageType() {
        return SERVER_TO_CLIENT_REFRESH_FRIENDS;
    }

    @Override
    public byte[] popPack() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(intTobytes(this.getFriendsBytes(QQserver.getInstance().getFriendsBytes()).length));
        return this.getFriendsBytes(QQserver.getInstance().getFriendsBytes());
    }
}
