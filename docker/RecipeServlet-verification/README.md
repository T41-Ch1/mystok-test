mystok.war更新時、mystok.warが展開されたmystokフォルダを消さないと、java,jspが入ってないmystokフォルダになってしまう
load data でauto_incrementの列をcsvファイル上で空白にする場合
load data infile '/var/lib/mysql-files/ryourimei_data.csv' into table RyourimeiTB fields terminated by ',' lines terminated by '\r\n'(Ryourimei);とする
csvファイルで
,じゃがいも
はだめ
じゃがいも
と書くべき
