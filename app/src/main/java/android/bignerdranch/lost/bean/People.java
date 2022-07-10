package android.bignerdranch.lost.bean;

import java.util.Date;
import java.util.UUID;

public class People {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public People() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

}
