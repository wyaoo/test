package test;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class test {

	public static void main(String[] args) {

		String cdNo = "6222600110041846358";
		System.out.println(getCdNoChkBit(StringUtils.substring(cdNo, 0, cdNo.length() - 1)));
		// System.out.println(" card: " + cdNo);
		// System.out.println("check code: " +
		// getCdNoChkBit(StringUtils.left(cdNo, 15)));
		// System.out.println("是否为银行卡:" + checkBankCard(cdNo));
	}

	/**
	 * 校验银行卡卡号
	 * 
	 * @param cardId
	 * @return
	 */
	public static boolean checkBankCard(String cdNo) {
		/*
		 * 长度检查
		 */
		int length = StringUtils.length(cdNo);
		int[] validLength = { 16, 17, 19 };
		if (!ArrayUtils.contains(validLength, length)) {
			return false;
		}
		/*
		 * 校验位检查
		 */
		char bit = getCdNoChkBit(StringUtils.substring(cdNo, 0, cdNo.length() - 1));
		if (bit == 'N' || cdNo.charAt(cdNo.length() - 1) != bit) {
			return false;
		}
		return true;
	}

	public static char getCdNoChkBit(String str) {
		/*
		 * 数值检查
		 */
		if (StringUtils.length(str) == 0 || !str.matches("\\d+")) {
			return 'N';
		}
		/*
		 * Luhm算法
		 */
		char[] chs = str.trim().toCharArray();
		int luhmSum = 0;
		for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
			int k = chs[i] - '0';
			if (j % 2 == 0) {
				k *= 2;
				k = k / 10 + k % 10;
			}
			luhmSum += k;
		}
		return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
	}
}