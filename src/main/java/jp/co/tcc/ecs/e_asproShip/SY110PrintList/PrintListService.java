package jp.co.tcc.ecs.e_asproShip.SY110PrintList;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jp.co.tcc.ecs.e_asproComm.common.CSVUtil;
import jp.co.tcc.ecs.e_asproComm.common.csv.CsvDownloader;
import jp.co.tcc.ecs.e_asproUser.User.User;
import jp.co.tcc.ecsolution.framework.dbUtils.SQLParamType;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;
/**
 * [�T �v]:�o�׎w���f�[�^�@Service<br>
 * [�� ��]:<br>
 * [�� �l]:<br>
 */
public class PrintListService {
	PrintListDAO dao = new PrintListDAO();
	/**
	 * [�T �v]:�h���b�v�_�E�����X�g�̏��������s���B<br>
	 * [�� ��]:�h���b�v�_�E�����X�g�̏��������s���B<br>
	 * [�� �l]:
	 *
	 * @param bean
	 */ 
	public void initDropDownList(PrintListBean bean) throws Exception {
		
		// �o�͏󋵃��X�g
		List<Map<String, String>> downFlgList = new ArrayList<Map<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("KEY", "0");
		map.put("VALUE", "");
		downFlgList.add(map);
		
		map = new HashMap<String, String>();
		map.put("KEY", "1");
		map.put("VALUE", "���o��");
		downFlgList.add(map);
		
		map = new HashMap<String, String>();
		map.put("KEY", "2");
		map.put("VALUE", "�o�͍�");
		downFlgList.add(map);
		bean.setDownFlgList(downFlgList);
		
	}
	

	public PrintListBean search(PrintListBean bean) throws Exception{
		
		bean = dao.search(bean);
		return bean;
	}


	
	/**
	 * [�T �v]:�o�׎w��CSV�o�́B<br>
	 * @function downloadCsv
	 * @param response
	 * @param bean
	 * @return String
	 * @throws Exception
	 */
	public PrintListBean downLoadCSV(User user, PrintListBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/** 
	 * [�T�v]:�o�׈ꗗ<br>
	 * [�� ��]:<br>
	 * @param bean
	 * @return bean
	 * @throws Exception
	 */

	public PrintListBean showCSV(User user, PrintListBean bean) {
		// TODO Auto-generated method stub
		return bean;
	}
	
	
	
	/**
	 * �o�׎w�����
	 * @function update
	 * @param  response
	 * @param  bean
	 * @return String
	 * @throws Exception
	 */
	
	
	
	/**
	 * �o�׎w���m��
	 * @function update
	 * @param  response
	 * @param  bean
	 * @return String
	 * @throws Exception
	 */
	
	public PrintListBean updateCSV(PrintListBean bean) {
		// TODO Auto-generated method stub
		return null;
	}


	@SuppressWarnings("static-access")
	public String downLoad(HttpServletResponse response, PrintListBean bean) throws Exception {
		String errM = "";
		// �w�b�_CSV�o��
		String fileName = "";
		if (bean.CSV_HEAD_DOWN.equals(bean.getCsv_down_kbn())) {
			fileName = "OrdersList_Header";
		}
		// ����CSV�o��
		if (bean.CSV_LIST_DOWN.equals(bean.getCsv_down_kbn())) {
			fileName = "OrdersList_Detail";
		}
		// �p�����[�^�Z�b�g
		bean.setIntPageNo(1);
		bean.setIntPageSize(999999999);
		List<SQLParamType> listParams = dao.setListParams(bean, "1");

		// CSV�o��
		String csvDownKbn = bean.getCsv_down_kbn();
		CsvDownloader downloader = new CsvDownloader(response, fileName, String.valueOf(bean.getLoginUser().getCoNo()), csvDownKbn, StringUtil.nvl(bean.getPattern_no(),"1"));
		if (!downloader.download("SY110.search", listParams, "LIST", true)) {
			errM = downloader.getErrorMessage();
		}
		return errM;
	}
	
	/**
	 * [�T �v]:CSV�f�[�^�쐬�B<br>
	 * [�� ��]:�w�肳�ꂽFile��CSV�f�[�^������Ă����܂��B<br>
	 * [�� �l]:
	 *
	 * @param List
	 * @param header_flg
	 * @param itemCnt
	 * @param item_name
	 * @param out
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private void makeCSV2(PrintListBean bean, int header_flg, int pageCount, FileOutputStream out) throws Exception {

		List<Map<String, String>> List = bean.getListCsv();
		if (List.size() > 0) {
			// �w�b�_�[�o�͔���
			if (header_flg == 1) {
				// �w�b�_�[�f�[�^�o��
				String title = "No,���P�[�V����,�݌ɏ��i�R�[�h,JAN�R�[�h,�݌ɏ��i��,����,�P��\r\n";
				out.write(title.toString().getBytes("Windows-31J"));
			}

			// �؂蕪�����܂�No��i�߂�B
			int noCnt = bean.getIntPageSize() * (pageCount - 1);

			for (int i = 0; i < List.size(); i++) {
				StringBuilder sbText = new StringBuilder();

				// No
				sbText.append(CSVUtil.varchar(StringUtil.nvl(noCnt + i + 1), true) + ",");
				// ���P�[�V����
				sbText.append(CSVUtil.varchar(StringUtil.nvl(List.get(i).get("LOCATION_CD")), true)).append(",");
				// �݌ɏ��i�R�[�h
				sbText.append(CSVUtil.number(StringUtil.nvl(List.get(i).get("ZAIKO_ITEM_NO")))).append(",");
				// JAN�R�[�h
				sbText.append(CSVUtil.number(StringUtil.nvl(List.get(i).get("JAN_CD")))).append(",");
				// �݌ɏ��i��
				sbText.append(CSVUtil.number(StringUtil.nvl(List.get(i).get("ZAIKO_ITEM_NM1")))).append(",");
				// ����
				sbText.append(CSVUtil.number(StringUtil.nvl(List.get(i).get("QTY")))).append(",");
				// �P��
				sbText.append(CSVUtil.number(StringUtil.nvl(List.get(i).get("MARK_NM")))).append("\r\n");

				out.write(String.valueOf(sbText.toString()).getBytes("Windows-31J"));
			}
		}
	}
	
	
	
	
	
}
