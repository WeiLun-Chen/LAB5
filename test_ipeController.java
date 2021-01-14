package org.eclipse.om2m.sample.test_ipe.controller;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.om2m.commons.constants.MimeMediaType;
import org.eclipse.om2m.commons.obix.Bool;
import org.eclipse.om2m.commons.obix.Obj;
import org.eclipse.om2m.commons.obix.Str;
import org.eclipse.om2m.commons.obix.io.ObixEncoder;
import org.eclipse.om2m.commons.resource.ContentInstance;
import org.eclipse.om2m.core.service.CseService;
import org.eclipse.om2m.sample.test_ipe.RequestSender;
import org.eclipse.om2m.sample.test_ipe.constants.test_ipeConstants;
import org.eclipse.om2m.sample.test_ipe.model.test_ipeModel;

public class test_ipeController {

	public static CseService CSE;
	protected static String AE_ID;
	private static Observer GUIOBSERVER = null;

	public static void setGUIOBSERVER(Observer obs) {
		GUIOBSERVER = obs;
	}

	public static interface Observer {
		void StateChange(String msg);
	}

	private static void createDATA() {
		// notify GUI
		notifyObserver(test_ipeController.getAirConState());
		// Send the information to the CSE
		String targetID = test_ipeConstants.CSE_PREFIX + "/" + test_ipeConstants.AE_NAME + "/" + test_ipeConstants.DATA;
		ContentInstance cin = new ContentInstance();
		Obj obj = new Obj();
		obj.add(new Bool("on/off", test_ipeModel.getAirConValue()));
		obj.add(new Str("Temperature", String.valueOf(test_ipeModel.getAirConTemp())));
		obj.add(new Str("Fan", String.valueOf(test_ipeModel.getAirConFan())));
		cin.setContent(ObixEncoder.toString(obj));
		cin.setContentInfo(MimeMediaType.OBIX + ":" + MimeMediaType.ENCOD_PLAIN);
		RequestSender.createContentInstance(targetID, cin);
	}

	private static void notifyObserver(final String msg) {
		new Thread() {
			@Override
			public void run() {
				GUIOBSERVER.StateChange(msg);
			}
		}.start();
	}

	public static void setAirConON() {
		// Set the value in the "real world" model
		test_ipeModel.setAirConON();
		// Send the information to the CSE
		createDATA();
	}

	public static void setAirConOFF() {
		// Set the value in the "real world" model
		test_ipeModel.setAirConOFF();
		// Send the information to the CSE
		createDATA();
	}

	public static void switchAirCon() {
		// Set the value in the "real world" model
		if (test_ipeModel.getAirConValue() == false) {
			test_ipeController.setAirConON();
		} else {
			test_ipeController.setAirConOFF();
		}
	}

	public static String getAirConState() {
		String state = String.valueOf(test_ipeModel.getAirConValue());
		state += ',' + String.valueOf(test_ipeModel.getAirConTemp());
		state += ',' + String.valueOf(test_ipeModel.getAirConFan());
		return state;
	}

	public static boolean setTemp(String PM) {
		boolean res = false;
		if (PM.equals("plus")) {
			// Set the value in the "real world" model
			res = test_ipeModel.setTempPlus();
		} else if (PM.equals("minus")) {
			// Set the value in the "real world" model
			res = test_ipeModel.setTempMinus();
		}
		createDATA();
		return res;
	}

	public static boolean setFan(String PM) {
		boolean res = false;
		if (PM.equals("plus")) {
			// Set the value in the "real world" model
			res = test_ipeModel.setFanPlus();
		} else if (PM.equals("minus")) {
			// Set the value in the "real world" model
			res = test_ipeModel.setFanMinus();
		}
		createDATA();
		return res;
	}

	public static void setCse(CseService cse) {
		CSE = cse;
	}

}