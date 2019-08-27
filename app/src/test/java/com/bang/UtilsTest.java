package com.bang;

import com.bang.helper.Utils;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class UtilsTest {

    @Test
    public void testIsEmailValid(){
        String testEmail = "anilupadhyay.mindiii@gmail.com";
        Assert.assertThat(String.format("Email Validity Test failed for %s ", testEmail), Utils.checkEmailForValidity(testEmail),is(true));
    }

    @Test
    public void testEmailValidityPartTwo(){
        String testEmail = "     anilupadhyay.mindiii@gmail.com     ";
        Assert.assertThat(String.format("Email Validity Test failed for %s ", testEmail), Utils.checkEmailForValidity(testEmail), is(true));
    }

    @Test
    public void emailStringNullCheck() {
        Assert.assertThat(Utils.emailStringChecker(null), Is.is(""));
    }

    @Test
    public void emailStringEmptyCheck() {
        Assert.assertThat(Utils.emailStringChecker(""), Is.is(""));
    }

}

