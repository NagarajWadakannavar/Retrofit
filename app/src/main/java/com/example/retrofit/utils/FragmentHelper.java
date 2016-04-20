package com.example.retrofit.utils;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * Created by NAGARAJ on 4/20/2016.
 */
public class FragmentHelper {

    public static void add(FragmentActivity activity,Fragment fragment,int containerID){
        activity.getSupportFragmentManager().beginTransaction().add(containerID,fragment).commitAllowingStateLoss();
    }

    public static void replace(FragmentActivity activity,Fragment fragment,int containerID){
        activity.getSupportFragmentManager().beginTransaction().replace(containerID,fragment).commitAllowingStateLoss();
    }
}
