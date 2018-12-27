package com.example.where.widget;

import android.content.ContentResolver;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore.Images.ImageColumns;

public final class GlobalStatic {
	
	private static String telephone;//登陆手机号
    private static String userid ;
    private static String imgUrl;
	/**
	 * @return the telephone
	 * @author 臻鸿
	 * 2015年9月12日 下午15:58
	 */
	public static String getTelephone() {
		return telephone;
	}
	/**
	 * @param Telephone the telephone to set
	* @author 臻鸿
	 * 2015年9月12日 下午15:58
	 */
	public static void setTelephone(String telephone) {
		GlobalStatic.telephone = telephone;
	}
	/**
	 * @return the userid
	* @author 臻鸿
	 * 2015年9月12日 下午15:58
	 */
	public static String getUserid() {
		return userid;
	}
	/**
	 * @param userid the userid to set
	 *@author：石头
	 *2015年8月31日 上午11:50:08
	 */
	public static void setUserid(String userid) {
		GlobalStatic.userid = userid;
	}
    

	public static String getImgUrl() {
		return imgUrl;
	}
	public static void setImgUrl(String imgUrl) {
		GlobalStatic.imgUrl = imgUrl;
	}
	/**
	 * Try to return the absolute file path from the given Uri
	 *
	 * @param context
	 * @param uri
	 * @return the file path or null
	 */
	public static String getRealFilePath( final Context context, final Uri uri ) {
	    if ( null == uri ) return null;
	    final String scheme = uri.getScheme();
	    String data = null;
	    if ( scheme == null )
	        data = uri.getPath();
	    else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
	        data = uri.getPath();
	    } else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
	        Cursor cursor = context.getContentResolver().query( uri, new String[] { ImageColumns.DATA }, null, null, null );
	        if ( null != cursor ) {
	            if ( cursor.moveToFirst() ) {
	                int index = cursor.getColumnIndex( ImageColumns.DATA );
	                if ( index > -1 ) {
	                    data = cursor.getString( index );
	                }
	            }
	            cursor.close();
	        }
	    }
	    return data;
	}
	
}