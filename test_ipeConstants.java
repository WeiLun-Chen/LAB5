package org.eclipse.om2m.sample.test_ipe.constants;

import org.eclipse.om2m.commons.constants.Constants;

public class test_ipeConstants {

	private test_ipeConstants() {
	}

	public static final String POA = "test_ipe";
	public static final String DATA = "DATA";
	public static final String AE_NAME = "Air_Conditioner";
	public static final String QUERY_STRING_OP = "op";

	public static String CSE_ID = "/" + Constants.CSE_ID;
	public static String CSE_PREFIX = CSE_ID + "/" + Constants.CSE_NAME;
}