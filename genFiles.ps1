$mID = [guid]::newguid()
$s = "<root>
    <mID>{0}</mID>
    <action>{1}</action>
</root>" -f $mID, "proc2"
write-host $s
#$q = "select count(*) from test"
$q = "INSERT INTO `"public`".test(id, `"text`")VALUES('{0}', 'test');" -f [guid]::newguid()

set-content -path "I:\Coding\Scala\ActivatorScala\camelHttp\target\in\$mID.xml" $q