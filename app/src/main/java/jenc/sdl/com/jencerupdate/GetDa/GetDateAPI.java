package jenc.sdl.com.jencerupdate.GetDa;

public interface GetDateAPI {
    public void getWeight(String scaleWeight, String weight, boolean isStale);
    public void DivOpenSuccess();
    public void zeroSetingSuc();
    public void zeroSetingFail();
    public void ScaleSuc();
    public void ScaleFail();
    public void DivOpenFail();
    public void getOtherdate(byte[] tys, int size);
}
