package jp.co.tcc.ecs.e_asproShip.SY050ShijiFixEntry;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jp.co.tcc.ecs.e_asproComm.common.Properties;
import jp.co.tcc.ecs.e_asproComm.common.csv.CsvDownloader;
import jp.co.tcc.ecsolution.framework.dbUtils.DBHelper;
import jp.co.tcc.ecsolution.framework.dbUtils.SQLParamType;

public class ShijiFixEntryService {
	ShijiFixEntryDAO dao = new ShijiFixEntryDAO();

	/**
	 * [�T �v]:�ꗗ�����B<br>
	 * [�� ��]:<br>
	 * [�� �l]:
	 *
	 * @param ShijiFixEntryBean
	 *            bean
	 * @return ShijiFixEntryBean
	 * @throws Exception
	 */
	public ShijiFixEntryBean search(ShijiFixEntryBean bean) throws Exception {
		ShijiFixEntryDAO dao = new ShijiFixEntryDAO();
		bean = dao.search(bean);
		return bean;
	}

	/**
	 * [�T �v]:�I���m��B<br>
	 * [�� ��]:<br>
	 * [�� �l]:
	 *
	 * @param ShijiFixEntryBean
	 *            bean
	 * @throws Exception
	 */
	public int kobetu(ShijiFixEntryBean bean) throws Exception {
		ShijiFixEntryDAO dao = new ShijiFixEntryDAO();
		return dao.kobetu(bean);
	}

	/**
	 * [�T �v]:�ꊇ�m��B<br>
	 * [�� ��]:<br>
	 * [�� �l]:
	 *
	 * @param ShijiFixEntryBean
	 *            bean
	 * @throws Exception
	 */
	public int ikkatu(ShijiFixEntryBean bean) throws Exception {
		ShijiFixEntryDAO dao = new ShijiFixEntryDAO();
		return dao.ikkatu(bean);
	}

	public boolean excl(ShijiFixEntryBean bean) {
		// DB�Ƃ̐ڑ����쐬����
		boolean exclFlag = false;
		try {
			ShijiFixEntryDAO dao = new ShijiFixEntryDAO();
			exclFlag = dao.checkExcl(bean);

		} catch (Exception e) {
			exclFlag = false;
		}
		return exclFlag;
	}

	/**
	 * [�T �v]:CSV�o�́B<br>
	 *
	 * @param response
	 * @param bean
	 * @return errM
	 * @throws Exception
	 */
	public String downLoad(HttpServletResponse response, ShijiFixEntryBean bean) throws Exception {
		String fileName = null;
		if (bean.CSV_DOWN_KBN.equals(bean.getCsvDownKbn())) {
			fileName = "ShijiFixEntry";
		} else {
			return Properties.getMsg("HA.ERROR.132", true);
		}

		List<SQLParamType> parameterList = dao.setListParams(bean);
		parameterList.add(DBHelper.createSQLParamVarchar2(bean.getCsvDownKbn()));
		parameterList.add(DBHelper.createSQLParamVarchar2(bean.PATTERN_NO));
		parameterList.add(DBHelper.createSQLOutParamCursor(dao.RESULT));

		CsvDownloader downloader = new CsvDownloader(response, fileName,String.valueOf(bean.getLoginUser().getCoNo()), bean.getCsvDownKbn(), bean.PATTERN_NO);
		if (!downloader.download(dao.SY050_CSV_DOWNLOAD, parameterList, dao.RESULT, true)) {
			return downloader.getErrorMessage();
		}
		return "";
	}

}