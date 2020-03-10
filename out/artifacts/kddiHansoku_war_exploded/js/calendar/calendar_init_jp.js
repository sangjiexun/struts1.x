Calendar._DN = new Array
(" 日",
 " 月",
 " 火",
 " 水",
 " 木",
 " 金",
 " 土",
 " 日");

// short day names
Calendar._SDN = new Array
("日",
 "月",
 "火",
 "水",
 "木",
 "金",
 "土",
 "日");

// First day of the week. "0" means display Sunday first, "1" means display
// Monday first, etc.
Calendar._FD = 0;

// full month names
Calendar._MN = new Array
("1月",
 "2月",
 "3月",
 "4月",
 "5月",
 "6月",
 "7月",
 "8月",
 "9月",
 "10月",
 "11月",
 "12月");

// short month names
Calendar._SMN = new Array
("1月",
 "2月",
 "3月",
 "4月",
 "5月",
 "6月",
 "7月",
 "8月",
 "9月",
 "10月",
 "11月",
 "12月");
// tooltips
Calendar._TT = {};
Calendar._TT["INFO"] = "help";

Calendar._TT["ABOUT"] ="日付選択:\n- \xab, \xbb ボタンをクリックして年を選択できます\n- "+ String.fromCharCode(0x2039) +", "+ String.fromCharCode(0x203a) +  " ボタンをクリックして月を選択できます\n- 以上のボタンを長く押すと年または月のクイックサーチできます";
Calendar._TT["ABOUT_TIME"] = "\n\n時間選択:\n- 時或は分をクリックするとプラス1になります\n- Shift＋時或は分をクリックするとマイナス1になります\n- ドラッグすると早く検索できます";
Calendar._TT["PREV_YEAR"] = "前年";
Calendar._TT["PREV_MONTH"] = "前月";
Calendar._TT["GO_TODAY"] = "当日選択";
Calendar._TT["NEXT_MONTH"] = "翌月";
Calendar._TT["NEXT_YEAR"] = "翌年";
Calendar._TT["SEL_DATE"] = "日付選択";
Calendar._TT["DRAG_TO_MOVE"] = "ウィンドウの移動";
Calendar._TT["PART_TODAY"] = " (今日)";

// the following is to inform that "%s" is to be the first day of week
// %s will be replaced with the day name.
Calendar._TT["DAY_FIRST"] = "左側一列目に表示%s";

// This may be locale-dependent.  It specifies the week-end days, as an array
// of comma-separated numbers.  The numbers are from 0 to 6: 0 means Sunday, 1
// means Monday, etc.
Calendar._TT["WEEKEND"] = "0,6";

Calendar._TT["CLOSE"] = "閉じる";
Calendar._TT["TODAY"] = "当日選択";
Calendar._TT["TIME_PART"] = "(Shift-)クリックまたはドラッグして値を変える";

// date formats
Calendar._TT["DEF_DATE_FORMAT"] = "%Y-%m-%d";
Calendar._TT["TT_DATE_FORMAT"] = "%A, %b %e日";

Calendar._TT["WK"] = "週";
Calendar._TT["TIME"] = "時間:";