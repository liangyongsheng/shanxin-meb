sample
===
	select #use("cols")# from meb_property where #use("condition")#
cols
===
	id,mebId,type,value,flag,createTime,createOprtId,lastUpdateTime,lastUpdateOprtId
condition
===
	1 = 1
	and flag = 1
	@if(!isEmpty(id)){
		and `id`=#id#
	@}
	@if(!isEmpty(mebId)){
		and `mebId`=#mebId#
	@}
