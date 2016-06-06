sample
===
	select #use("cols")# from #text(tableName)# where #use("condition")#
cols
===
	id,fieldName,`key`,`value`,createTime,remark

condition
===
	1 = 1
	@if(!isEmpty(id)){
		and `id`=#id#
	@}
	@if(!isEmpty(fieldName)){
		and `fieldName`=#fieldName#
	@}
	@if(!isEmpty(key)){
		and `key`=#key#
	@}
	