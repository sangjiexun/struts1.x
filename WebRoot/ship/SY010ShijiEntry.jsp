<%@ page language="java" contentType="text/html; charset=windows-31j"
  pageEncoding="windows-31j"%>
<%@include file="/taglibs.jsp"%>
<%@page import="jp.co.tcc.ecs.e_asproShip.SY010ShijiEntry.SY010ShijiEntryBean"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.framework.base.EscapeBean"%>
<%@page import="jp.co.tcc.ecsolution.framework.otherUtils.StringUtil"%>
<%@page import="jp.co.tcc.ecs.e_asproComm.common.Properties"%>
<%@page import="java.util.*"%>
<%
try{
	SY010ShijiEntryBean bean = (SY010ShijiEntryBean) request.getAttribute("bean");
	EscapeBean.escapeBeanAllProperty(bean);
	String btn_disabled = "";
	String title = "�o�׎w���ڍ�";
	String titleIcon = Constants.DOC_ROOT+"/img/head_kefir_syukka.gif";
%>
<HTML>

	<HEAD>
		<META HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html;charset=Shift_JIS">
		<LINK HREF="system.css" TYPE="text/css" REL="stylesheet" MEDIA="all">
		<TITLE></TITLE>
		<script src="system.js" type="text/javascript"></script>

		<SCRIPT type="text/javascript">
			function change1(num)
			{
				if(num == 1){
					document.all["div1"].style.display = 'none';
					document.all["div2"].style.display = 'block';
		         }else if (num == 2){
					document.all["div1"].style.display = 'block';
					document.all["div2"].style.display = 'none';
				}
			}
			</SCRIPT>
	</HEAD>
	<BODY BGCOLOR="#ffffff" LINK="#000066" VLINK="#000066" BGCOLOR="#FFFFFF" ONLOAD="">
		<DIV ID="Stock_table">
			<IMG SRC="<%=titleIcon %>" ALT="" HEIGHT="20" WIDTH="23" ALIGN="middle" BORDER="0">
			<div class="pt"><%=title %>
				<app:navigator request="<%= request%>"/>
			</div>
			<BR>
			<BR>
			<TABLE WIDTH="907" BORDER="0" CELLSPACING="1" CELLPADDING="1">
				<TR>
					<TD VALIGN="top" WIDTH="450"><FONT SIZE="2" COLOR="#ff9900">��</FONT>
						<FONT SIZE="2">�󒍊�{���<BR></FONT>
						<TABLE class="nb">
							<TR>
								<TD>
									<TABLE class="ft" style="width:440">
										<col width="80"/>
										<col width="140"/>
										<col width="80"/>
										<col width="140"/>
										<TR>
											<TH>�����ԍ�</TH>
											<TD><%=bean.getOrder_cd() %></TD>
											<TH>��������</TH>
											<TD><%=bean.getOrder_ymd_hms()%></TD>
										</TR>
										<TR>
											<TH>�x��</TH>
											<TD><%=bean.getSisya_name() %></TD>
											<TH>�q��</TH>
											<TD><%=bean.getWh_nm() %></TD>
										</TR>
										<TR>
											<TH>�o�׋敪</TH>
											<TD><%=bean.getShip_kbn_nm() %></TD>
											<TH>������</TH>
											<TD><%=bean.getSyoriKbnNm() %></TD>
										</TR>
									</TABLE>
								</TD>
							</TR>
						</TABLE>
						<FONT SIZE="2" COLOR="#ff9900">��</FONT>
						<FONT SIZE="2">��t�X���/���n�X���<BR></FONT>
						<DIV ID="div1" STYLE="display:block;">
							<TABLE class="nb" style="border-collapse:collapse;">
								<col width="125"/>
								<col width="125"/>
								<col width="125"/>
								<TR HEIGHT="22">
									<TD ALIGN="center" BACKGROUND="<%=Constants.DOC_ROOT%>/img/tab_s.gif"><FONT SIZE="2">��t�X���</FONT></TD>
									<TD ALIGN="center" BGCOLOR="#cccccc" BACKGROUND="<%=Constants.DOC_ROOT%>/img/tab_s.gif">
									<FONT SIZE="2"><A HREF="JavaScript:change1(1);">���n�X���</A></FONT>
									</TD>
									<TD ALIGN="center"></TD>
								</TR>
							</TABLE>
							<TABLE class="nb" style="border-collapse:collapse;">
								<TR>
									<TD>
										<TABLE CLASS="ft" style="width:440">
											<col width="80"/>
											<col width="140"/>
											<col width="80"/>
											<col width="140"/>
											<TR>
												<TH>��t�XCD</TH>
												<TD colspan="3"><%=bean.getKokyaku_cd()%></TD>
											</TR>
											<TR>
												<TH>��t�X��</TH>
												<TD colspan="3">
													1�F<%=bean.getKokyaku_nm1()%>
													2�F<%=bean.getKokyaku_nm2()%>
												</TD>
											</TR>
											<TR>
												<TH>��t�X���J�i</TH>
												<TD colspan="3">
													1�F<%=bean.getKokyaku_kana1()%>
													2�F<%=bean.getKokyaku_kana2()%>
												</TD>
											</TR>
											<TR>
												<TH>��t�X�Z��</TH>
												<TD colspan="3">
													��<%=bean.getKokyaku_zip()%><BR>
													�Z��1�F<%=bean.getKokyaku_addr1()%><BR>
													�Z��2�F<%=bean.getKokyaku_addr2()%><BR>
													�Z��3�F<%=bean.getKokyaku_addr3()%>
												</TD>
											</TR>
											<TR>
												<TH>�d�b�ԍ�</TH>
												<TD colspan="3">
													1�F<%=bean.getKokyaku_tel1()%>
													2�F<%=bean.getKokyaku_tel2()%>
												</TD>
											</TR>
											<TR>
												<TH>FAX</TH>
												<TD colspan="3">
													1�F<%=bean.getKokyaku_fax()%>
												</TD>
											</TR>
											<TR>
												<TH>���[��</TH>
												<TD colspan="3">
													1�F<%=bean.getKokyaku_mail1()%><br/>
													2�F<%=bean.getKokyaku_mail2()%><br/>
													3�F<%=bean.getKokyaku_mail3()%>
												</TD>
											</TR>
										</TABLE>
									</TD>
								</TR>
							</TABLE>
						</DIV>
						<DIV ID="div2" STYLE="display:none;">
							<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0">
								<col width="125"/>
								<col width="125"/>
								<col width="125"/>
								<TR HEIGHT="22">
									<TD ALIGN="center" BGCOLOR="#cccccc" BACKGROUND="<%=Constants.DOC_ROOT%>/img/tab_s.gif"><FONT SIZE="2">
										<A HREF="JavaScript:change1(2);">��t�X���</A>
									</FONT></TD>
									<TD ALIGN="center" BACKGROUND="<%=Constants.DOC_ROOT%>/img/tab_s.gif"><FONT SIZE="2">���n�X���</FONT></TD>
									<TD style="font-size:10">��t�X�ƈقȂ�ꍇ�ɓ���</TD>
								</TR>
							</TABLE>
							<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0">
								<TR>
									<TD>
										<TABLE CLASS="ft" style="width:440">
											<col width="80"/>
											<col width="360"/>
											<TR>
												<TH>���n�XCD</TH>
												<TD colspan="3"><%=bean.getTdk_cd()%></TD>
											</TR>
											<TR>
												<TH>���n�X��</TH>
												<TD>
													1�F<%=bean.getTdk_nm1()%>
													2�F<%=bean.getTdk_nm2()%>
												</TD>
											</TR>
											<TR>
												<TH>���n�X���J�i</TH>
												<TD>
													1�F<%=bean.getTdk_kana1()%>
													2�F<%=bean.getTdk_kana2()%>
												</TD>
											</TR>
												<TR>
												<TH>���n�X�Z��</TH>
												<TD>��<%=bean.getTdk_zip()%><BR>
													�Z��1�F<%=bean.getTdk_addr1()%><BR>
													�Z��2�F<%=bean.getTdk_addr2()%><BR>
													�Z��3�F<%=bean.getTdk_addr3()%>
												</TD>
											</TR>
											<TR HEIGHT="22">
												<TH>�d�b�ԍ�</TH>
												<TD>
													1�F<%=bean.getTdk_tel1()%>
													2�F<%=bean.getTdk_tel2()%>
												</TD>
											</TR>
										</TABLE>
									</TD>
								</TR>
							</TABLE>
						</DIV>
					</TD>
					<TD VALIGN="top" WIDTH="450"><FONT SIZE="2" COLOR="#ff9900">��</FONT>
						<FONT SIZE="2" COLOR="#ff9900">��</FONT><FONT SIZE="2">�z�����<BR>
						</FONT>
						<TABLE class="nb">
							<TR>
								<TD>
									<TABLE CLASS="ft" style="width:440">
										<col width="80"/>
										<col width="140"/>
										<col width="80"/>
										<col width="140"/>
										<TR>
											<TH>�z�B�w���</TH>
											<TD><%=bean.getTdk_ymd()%></TD>
											<TH>���ԑюw��</TH>
											<TD><%=bean.getHstm_kbn_nm()%></TD>
										</TR>
									</TABLE>
								</TD>
							</TR>
						</TABLE>
						<FONT SIZE="2" COLOR="#ff9900">��</FONT><FONT SIZE="2">�o�׏��<BR>
						</FONT>
						<TABLE class="nb">
							<TR>
								<TD>
									<TABLE CLASS="ft" style="width:440">
										<col WIDTH="80"/>
										<col WIDTH="140"/>
										<col WIDTH="80"/>
										<col WIDTH="140"/>
										<TR>
											<TH>�o�הԍ�</TH>
											<TD colspan="3"><%=bean.getSksj_cd()%></TD>
										</TR>
										<TR>
											<TH>�o�ח\���</TH>
											<TD><%=bean.getSyukka_yotei_ymd()%></TD>
											<TH>��</TH>
											<TD><%=bean.getKosu()%></TD>
										</TR>
										<TR>
											<TH>���₢���킹No</TH>
											<TD colspan="3">
											<%
												Map<String, String> tempMap1 = bean.getLstHead().get(0);
												String[] okurinos = null;
													if(null != tempMap1.get("OKURINO")){
														okurinos = tempMap1.get("OKURINO").split(",");
														for (int i = 1; i <= okurinos.length; i++) {
												%>
															<%=okurinos[i-1] + "&nbsp;" %>
															<% if (i % 3 == 0) { %>
															<%="<br>" %>
												<% 			}
														}
													}
												%>
											</TD>
										</TR>
										<TR>
											<TH>��Ɠ��L</TH>
											<TD colspan="3">
												<%=bean.getOrder_biko()%>
											</TD>
										</TR>

									</TABLE>
								</TD>
							</TR>
						</TABLE>

						<FONT SIZE="2" COLOR="#ff9900">��</FONT><FONT SIZE="2">�Ǘ����<BR>
						</FONT>
						<TABLE class="nb">
							<TR>
								<TD>
									<TABLE CLASS="ft" style="width:440">
										<col WIDTH="80"/>
										<col WIDTH="140"/>
										<col WIDTH="80"/>
										<col WIDTH="140"/>
										<TR>
											<TH>�o�^����</TH>
											<TD><%=bean.getMake_date()%></TD>
											<TH>�X�V����</TH>
											<TD><%=bean.getRep_date()%></TD>
										</TR>
										<TR>
											<TH>�X�V��</TH>
											<TD colspan="3"><%=bean.getRep_user_nm()%></TD>
										</TR>
									</TABLE>
								</TD>
							</TR>
						</TABLE>
					</TD>
				</TR>
			</TABLE>
			<TABLE BORDER="0" CELLSPACING="1" CELLPADDING="1">
				<TR>
					<TD><FONT SIZE="2" COLOR="#ff9900">��</FONT><FONT SIZE="2">���i���<BR>
						</FONT>
						<TABLE class="nb">
							<TR>
								<TD>
									<TABLE id="_itemTable" CLASS="lt">
										<col width="30">
										<col width="130">
										<col width="200">
										<col width="100">
										<col width="100">
										<col width="100">
										<col width="100">
										<col width="80">
										<col width="80">
										<col width="110">
										<TR CLASS="head">
											<TH>No</TH>
											<TH>���i�R�[�h</TH>
											<TH>���i��</TH>
											<TH>�̔����i<br/>�i�Ŕ��j</TH>
											<TH>�Z�b�g����</TH>
											<TH>��������</TH>
											<TH>�s�[�X���v</TH>
											<TH>���׌v</TH>
											<TH>�����</TH>
											<TH>���l</TH>
										</TR>
											<%List<Map<String,String>>  list = bean.getLstDetail(); %>
											<%for(int i=1;i<=list.size();i++){ %>
												<%Map<String,String> row =  list.get(i-1);
												String lv_SALE_PRICE = StringUtil.nvl(row.get("SALE_TNK"));
												String lv_QTY = StringUtil.nvl(row.get("QTY"));
												String lv_IRI_SU = StringUtil.nvl(row.get("IRI_SU"));
												String lv_SUM_QTY = StringUtil.nvl(row.get("SUM_QTY"));
												String lv_WV_KIN = StringUtil.nvl(row.get("WV_KIN"));
												String lv_MEI_ZEI_KIN = StringUtil.nvl(row.get("MEI_ZEI_TNK"));
												%>
												<TR style="min-height:22;">
													<TD ALIGN="center"><%=i%></TD>
													<TD><%=StringUtil.nvl(row.get("ITEM_NO")) %></TD>
													<TD title="<%=StringUtil.nvl(row.get("ITEM_NM"))%>">
														<nobr><%=StringUtil.nvl(row.get("ITEM_NM"))%></nobr>
													</TD>
													<TD ALIGN="right">
														<app:NumberFormat numberValue="<%=lv_SALE_PRICE%>"/>
													</TD>
													<TD ALIGN="right">
														<app:NumberFormat numberValue="<%=lv_QTY%>"/>
													</TD>
													<TD ALIGN="right">
														<app:NumberFormat numberValue="<%=lv_IRI_SU%>"/>
													</TD>
													<TD ALIGN="right">
														<app:NumberFormat numberValue="<%=lv_SUM_QTY%>"/>
													</TD>
													<TD ALIGN="right">
														<app:NumberFormat numberValue="<%=lv_WV_KIN%>"/>
													</TD>
													<TD ALIGN="right">
														<app:NumberFormat numberValue="<%=lv_MEI_ZEI_KIN%>"/>
													</TD>
													<TD title="<%=StringUtil.nvl(row.get("BIKOU"))%>">
														<nobr><%=StringUtil.nvl(row.get("BIKOU")) %></nobr>
													</TD>
												</TR>
											<%}%>
									</TABLE>
								</TD>
							</TR>
						</TABLE>
					</TD>
				</TR>
				<TR>
					<TD>
						<TABLE class="nb">
							<TR>
								<TD>
									<TABLE class="lt2">
										<col width="225"/>
										<col width="225"/>
										<col width="225"/>
										<col width="225"/>
										<TR BGCOLOR="#99ccff">
											<TH NOWRAP>���i<BR>���v����</TH>
											<TH NOWRAP>�������z(�Ŕ�)</TH>
											<TH NOWRAP>�����</TH>
											<TH NOWRAP>�������z(�ō�)</TH>
										</TR>
										<TR BGCOLOR="#eeeeee" HEIGHT="30">
											<TD ALIGN="right" STYLE="FONT:20px;">
												<app:NumberFormat numberValue="<%=bean.getQty_cnt()%>"/>
											</TD>
											<TD ALIGN="right" STYLE="FONT:20px;" BGCOLOR="#d8e0f5">
												<app:NumberFormat numberValue="<%=bean.getSeikyuu_kin()%>"/>
											</TD>
											<TD ALIGN="right" STYLE="FONT:20px;" BGCOLOR="#d8e0f5">
												<app:NumberFormat numberValue="<%=bean.getSeikyuu_zei_kin()%>"/>
											</TD>
											<TD ALIGN="right" STYLE="FONT:20px;" BGCOLOR="#d8e0f5">
												<app:NumberFormat numberValue="<%=bean.getSeikyuu_zei_k_kin()%>"/>
											</TD>
										</TR>
									</TABLE>
								</TD>
							</TR>
						</TABLE>
					</TD>
				</TR>
			</TABLE>
			<BR>
		</DIV>
	</BODY>

</HTML>
<%
} catch(Exception e){
	out.print(e);
} %>