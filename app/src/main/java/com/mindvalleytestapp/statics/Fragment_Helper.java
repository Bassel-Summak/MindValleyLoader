package com.mindvalleytestapp.statics;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import com.mindvalleytestapp.R;
import com.mindvalleytestapp.util.Toasts;

public class Fragment_Helper {


    // Animations tag on transactions
    final public static String Animation_FADE = "Animation_FADE";
    final public static String Animation_Swipe_to_right = "Animation_Swipe_to_right";
    final public static String Animation_Swipe_to_left = "Animation_Swipe_to_left";
    final public static String Animation_Bottom_To_Top = "Animation_Bottom_To_Top";
    final public static String Animation_Top_To_Bottom = "Animation_Top_To_Bottom";

    public static void FragmentReplace(final Fragment fragment, final AppCompatActivity appCompatActivity,
                                       final String animationType, final boolean storeInStack, boolean deleteAllFragmentsFromStack)
    {
        appCompatActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toasts.hideToast();

                final FragmentManager fm = appCompatActivity.getSupportFragmentManager();
                FragmentTransaction f = fm.beginTransaction();

                f = setFragmentAnimation(animationType,f);

                if (storeInStack && !isFragmnetInStack(appCompatActivity,(fragment.getClass().getName())))
                {
                    f.addToBackStack(fragment.getClass().getName());
                }

                // No Fragment_Default
                if (!isFragmnetInStack(appCompatActivity,(fragment.getClass().getName()))) {
                    f.replace(R.id.fragment_place, fragment, fragment.getClass().getName());
                    f.commitAllowingStateLoss();
                }
                else {
                    fm.popBackStackImmediate(fragment.getClass().getName(), 0);
                }
            }
        });
    }


    public static boolean isFragmnetInStack(AppCompatActivity mainActivity, String name)
    {
        FragmentManager fm = mainActivity.getSupportFragmentManager();
        if (fm.getBackStackEntryCount()==1)
            return false;
        else
        {
            for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                if (fm.getBackStackEntryAt(i) == null)
                    return false;
                else if (fm.getBackStackEntryAt(i).getName() ==null)
                    return false;
                else
                if (fm.getBackStackEntryAt(i).getName().equals(name))
                    return true;
            }
        }

        return false;
    }

    // Animate transaction
    private static FragmentTransaction setFragmentAnimation(String animationType,FragmentTransaction f)
    {
        if (animationType.equals(Animation_FADE))
            f.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in,R.anim.fade_out);
        else if (animationType.equals(Animation_Swipe_to_right))
            f.setCustomAnimations(R.anim.slide_in_right,
                    R.anim.slide_out_left, R.anim.slide_in_left,
                    R.anim.slide_out_right);
        else if (animationType.equals(Animation_Swipe_to_left))
            f.setCustomAnimations(R.anim.slide_in_left,
                    R.anim.slide_out_right, R.anim.slide_in_right,
                    R.anim.slide_out_left);
        else if (animationType.equals(Animation_Top_To_Bottom))
            f.setCustomAnimations(R.anim.slide_in_top_to_bottom,
                    R.anim.slide_out_top_to_bottom, R.anim.slide_in_top_to_bottom,
                    R.anim.slide_out_top_to_bottom);
        else if (animationType.equals(Animation_Bottom_To_Top))
            f.setCustomAnimations(R.anim.slide_bottom_to_top,
                    R.anim.slide_in_top_to_bottom, R.anim.slide_in_left,
                    R.anim.slide_out_right);

        return f;
    }


}
