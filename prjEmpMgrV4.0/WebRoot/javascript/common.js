function sortByNo(sort,empno,pageSize)
{
	var sort=empno+" "+sort;
	window.location.href="EmpServlet.do?op=1&sort="+sort+"&pageSize="+pageSize;
}

function query(pageSize)
{
	var field=document.getElementById("slt").value;
	var str=document.getElementById("str").value;
	if(field==null || field==""||str==null || str=="")
		{
			return false;
		}
	window.location.href="EmpServlet.do?op=1&field="+field+"&pageSize="+pageSize+"&str="+str;
}
//全选
function choice()
	   {
	   		var chioce=document.getElementById("choice");
	   		var tr=document.getElementsByTagName("tr");
	   		if(chioce.checked==true)
	   		{
	   		    for(var i=1;i<tr.length-1;i++)
	   		    {
		   		 document.getElementById(i).checked=true;
	   		    }
	   		}
	   		else
	   		{
	   			for(var i=1;i<tr.length-1;i++)
	   		    {
		   		 document.getElementById(i).checked=false;
	   		    }
	   		}
	   }
//删除
function deleteEmp(cell)
	   {
	        var flag=confirm("确定删除勾选的全部吗？");
	        var empList=new Array();
	        if(flag)
	        {
		        var tbody = document.getElementById("tbodyId");
		        var inputList=document.getElementsByName("choice");
		        for(var i=1;i<=inputList.length;i++)
		        {
					if(document.getElementById(i).checked==true)
					{
						var inputs=document.getElementById(i).parentNode.parentNode.getElementsByTagName("input");
						
						empList.push(inputs[2].value);
					}
		        }
		        window.location.href="EmpServlet.do?op=3&empList="+empList;
		     }
	        /*else
	        	{
	        	window.location.href="queryEmp.jsp";
	        	}*/
	   }
//修改
function editer(cell)
{
    var inputList=document.getElementsByName("choice");
    var checkedCount=0;
    var element=null;
    for(var i=1;i<=inputList.length;i++)
    	{
    	  if(document.getElementById(i).checked==true)
    		  {
    		  	 element=document.getElementById(i);
    		     checkedCount++;
    		  }
    	}
    if(checkedCount==0)
    	{
    		alert("请选择一条修改记录");
    	}
    else if(checkedCount>1)
    	{
    		alert("只能选择一条修改记录");
    	}
    else
    	{
    	   document.getElementById("choice").setAttribute('disabled','disabled');
	    	for(var i=1;i<=inputList.length;i++)
	    	{
	    	  if(document.getElementById(i).checked==false)
	    		  {
	    		   document.getElementById(i).setAttribute('disabled','disabled');
	    		  }
	    	}
	    	var ele=element.parentNode.parentNode;
    		var tdList=ele.getElementsByTagName("td");
    		var selectList=ele.getElementsByTagName("select");
    		selectList[0].removeAttribute('disabled');
    		for(var i=3;i<tdList.length;i++)
    			{
    			  if(i==3)
    				  {
    				    tdList[i].firstChild.select();
    				  }
    			  if(i==6)
    				  {
    				    tdList[i].onclick = function() 
    				    {
    		    		    WdatePicker({skin:'whyGreen',minDate:'1000-01-01',maxDate:'9999-12-31'});
    		    		 };
    				  }
    			  
    			   tdList[i].firstChild.readOnly = false;
    			}
    		cell.value="保存";
    		cell.setAttribute("onclick","save(this)");
    	}
}
//修改后保存
function save(cell)
{
	var empSet=new Array();
	var inputTbody=document.getElementsByName("choice");
	
	var element=null;
    for(var i=1;i<=inputTbody.length;i++)
    	{
    	  if(document.getElementById(i).checked==true)
    		  {
    		  	 element=document.getElementById(i);
    		  }
    	}
    var inputTr=element.parentNode.parentNode.getElementsByTagName("input");
    for(var i=2;i<inputTr.length;i++)
    	{
    	   empSet.push(inputTr[i].value);
    	}
    var ele=element.parentNode.parentNode;
	var selectList=ele.getElementsByTagName("select");
	empSet.push(selectList[0].value);
	selectList[0].setAttribute('disabled','disabled');
    document.getElementById("choice").removeAttribute('disabled');
	for(var i=1;i<=inputTbody.length;i++)
	{
	  document.getElementById(i).removeAttribute('disabled');
	}
	 cell.value="修改";
	 cell.setAttribute("onclick","editer(this)");
	 window.location.href="EmpServlet.do?op=4&empSet="+empSet; 
}
//增加
function add(cell)
{
	var theadId=document.getElementById("theadId");
	var tbody=document.getElementById("tbodyId");
	var trList=tbody.getElementsByTagName("tr");
	
	var inputCheckbox=document.createElement("input");
	inputCheckbox.setAttribute("type", "checkbox");
	inputCheckbox.setAttribute("name", "choice");
	inputCheckbox.setAttribute("checked", "checked");
	inputCheckbox.setAttribute("id", trList.length+1);
	inputCheckbox.className="checkbox";
	var tdCheckbox=document.createElement("td");
	tdCheckbox.appendChild(inputCheckbox);
	
	var inputIndex=document.createElement("input");
	inputIndex.setAttribute("type", "text");
	inputIndex.setAttribute("name", "index");
	inputIndex.setAttribute("value", trList.length+1);
	inputIndex.className="inputStyle";
	var tdIndex=document.createElement("td");
	tdIndex.className="padd";
	tdIndex.appendChild(inputIndex);
	
	var inputEmpno=document.createElement("input");
	inputEmpno.setAttribute("type", "text");
	inputEmpno.setAttribute("name", "empno");
	inputEmpno.setAttribute("value", "");
	inputEmpno.className="tdStyle";
	var tdEmpno=document.createElement("td");
	tdEmpno.className="padd";
	tdEmpno.appendChild(inputEmpno);
	
	var inputEname=document.createElement("input");
	inputEname.setAttribute("type", "text");
	inputEname.setAttribute("name", "ename");
	inputEname.setAttribute("value", "");
	inputEname.className="tdStyle";
	var tdEname=document.createElement("td");
	tdEname.className="padd";
	tdEname.appendChild(inputEname);
	
	var inputJod=document.createElement("input");
	inputJod.setAttribute("type", "text");
	inputJod.setAttribute("name", "job");
	inputJod.setAttribute("value", "");
	inputJod.className="tdStyle";
	var tdJob=document.createElement("td");
	tdJob.className="padd";
	tdJob.appendChild(inputJod);
	
	var inputMgr=document.createElement("input");
	inputMgr.setAttribute("type", "text");
	inputMgr.setAttribute("name", "mgr");
	inputMgr.setAttribute("value", "");
	inputMgr.className="tdStyle";
	var tdMgr=document.createElement("td");
	tdMgr.className="padd";
	tdMgr.appendChild(inputMgr);
	
	var inputDate=document.createElement("input");
	inputDate.setAttribute("type", "text");
	inputDate.setAttribute("name", "HireDate");
	inputDate.setAttribute("value", "");
	inputDate.onclick = function() {
	    WdatePicker({skin:'whyGreen',minDate:'1000-01-01',maxDate:'9999-12-31'});
	    //WdatePicker();
	  };
	inputDate.className="tdStyle";
	var tdDate=document.createElement("td");
	tdDate.className="padd";
	tdDate.appendChild(inputDate);
	
	
	var inputSal=document.createElement("input");
	inputSal.setAttribute("type", "text");
	inputSal.setAttribute("name", "sal");
	inputSal.setAttribute("value", "");
	inputSal.className="tdStyle";
	var tdSal=document.createElement("td");
	tdSal.className="padd";
	tdSal.appendChild(inputSal);
	
	var inputComm=document.createElement("input");
	inputComm.setAttribute("type", "text");
	inputComm.setAttribute("name", "comm");
	inputComm.setAttribute("value", "");
	inputComm.className="tdStyle";
	var tdComm=document.createElement("td");
	tdComm.className="padd";
	tdComm.appendChild(inputComm);
	
	var inputDeptno=document.createElement("select");
	inputDeptno.setAttribute("id","sc");
	var option="<option value=\" \">部门编号</option>";
	inputDeptno.options.add(new Option("部门编号",null));
	inputDeptno.options.add(new Option("ACCOUNTING","10"));
	inputDeptno.options.add(new Option("RESEARCH","20"));
	inputDeptno.options.add(new Option("SALES","30"));
	inputDeptno.options.add(new Option("OPERATIONS","40"));
	inputDeptno.className="tdStyle";
	var tdDeptno=document.createElement("td");
	tdDeptno.className="padd";
	tdDeptno.appendChild(inputDeptno);
	
	var newtr=document.createElement("tr");
	newtr.appendChild(tdCheckbox);
	newtr.appendChild(tdIndex);
	newtr.appendChild(tdEmpno);
	newtr.appendChild(tdEname);
	newtr.appendChild(tdJob);
	newtr.appendChild(tdMgr);
	newtr.appendChild(tdDate);
	newtr.appendChild(tdSal);
	newtr.appendChild(tdComm);
	newtr.appendChild(tdDeptno);
	theadId.appendChild(newtr);
	
	cell.value="保存";
	cell.setAttribute("onclick","addSave(this)");
}
//增加的保存
function addSave(cell)
{
	var addEmp=new Array();
	var nullEmp=new Array();
	var inputTbody=document.getElementsByName("choice");
	var element=null;
    for(var i=1;i<=inputTbody.length;i++)
    	{
    	  if(document.getElementById(i).checked==true)
    		  {
    		  	 element=document.getElementById(i);
    		  }
    	}
    var inputTr=element.parentNode.parentNode.getElementsByTagName("input");
    var flag=false;
    for(var i=2;i<inputTr.length;i++)
    	{
    	if(inputTr[i].value!=null)
    		{
    		 flag=true;
    		 addEmp.push(inputTr[i].value);
    		}
    	else
    		{
    		  nullEmp.push(inputTr[i]);
    		}
    	}
    var sc=document.getElementById("sc").value;
    if(flag && sc!="null")
    	{
    	
         addEmp.push(sc);
    	 cell.value="增加";
    	 cell.setAttribute("onclick","add(this)");
    	 window.location.href="EmpServlet.do?op=2&addEmp="+addEmp; 
    	}
    else
    	{
    	  alert("内容不能为空");
    	}
    
}

//选中就变色
function changeStyle(cell)
{
	var tdList=cell.parentNode.parentNode.getElementsByTagName("td");
		for(var i=1;i<tdList.length;i++)
		{
			if(cell.checked==true)
				{
				 tdList[i].className="showStyle";
				}
			else
				{
				 tdList[i].className="tab input";
				}
		}
}

//传递pageSize
function pageSize()
{
	var pageSize=document.getElementById("pageSize").value;
	 window.location.href="EmpServlet.do?op=1&pageSize="+pageSize;
}
//跳页
function choicePage()
{
	var choicePage=document.getElementById("choicePage").value;
	window.location.href="EmpServlet.do?op=1&choicePage="+choicePage;
}

