sample
===
	select #use("cols")# from meb where #use("condition")#
cols
===
	id,pwd,name,realName,nickName,flag,createTime,createOprtId,lastUpdateTime,lastUpdateOprtId,remark
condition
===
	1 = 1
	and flag = 1
	@if(!isEmpty(id)){
		and `id`=#id#
	@}
	@if(!isEmpty(name)){
		and `name`=#name#
	@}
	@if(!isEmpty(realName)){
		and `realName`=#realName#
	@}
	@if(!isEmpty(nickName)){
		and `nickName`=#nickName#
	@}
	
	
queryMeb
===
*-----------------------------------------------

	select
			#use("cols")# 
		from 
			meb 
		where 
			#use("queryMebCondt")# #use("queryMebLmt")#
queryMebCondt
===
	1 = 1
	and flag = 1
	@if(!isEmpty(id)){
		and `id`=#id#
	@}
	@if(!isEmpty(mobile)){
		and id in(select mebId from meb_property where flag = 1 and type = 1 and `value` = #mobile#)
	@}
	order by id desc
queryMebLmt
===
	@if(!isEmpty(pageIndex) && !isEmpty(pageCount)){
		limit #pageIndex#,#pageCount#
	@}
	@else if(!isEmpty(pageCount)){
		limit #pageCount#
	@}