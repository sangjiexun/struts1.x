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
	 * [概 要]:一覧検索。<br>
	 * [説 明]:<br>
	 * [備 考]:
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
	 * [概 要]:選択確定。<br>
	 * [説 明]:<br>
	 * [備 考]:
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
	 * [概 要]:一括確定。<br>
	 * [説 明]:<br>
	 * [備 考]:
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
		// DBとの接続を作成する
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
	 * [概 要]:CSV出力。<br>
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