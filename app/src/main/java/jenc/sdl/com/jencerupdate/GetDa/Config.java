package jenc.sdl.com.jencerupdate.GetDa;

public class Config {
    public static byte[] getWeight =new byte[]{0x13};//取重
    public static byte[] checkOpen =new byte[]{0x05};//检测端口打开

    public static byte[] scale =new byte[]{0x7E,0x00, (byte) 0xE5, (byte) 0x9B,0x7F};//去皮
    public static byte[] zeroSetting =new byte[]{0x7E,0x00, (byte) 0xE4, (byte) 0x9A,0x7F};//置零
}
