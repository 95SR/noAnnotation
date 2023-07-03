window.addEventListener("load", () => {
    console.log("load");
    getAllStudents();
});

const searchForm = document.getElementById("searchForm");
const addForm = document.getElementById("addForm"); 


const fetchData = async (url,method,data) => {
    let res;
    
    switch (method){
        case 'GET':
             res = await fetch(url,{
                method:method
            })
            break
        case 'POST':
            res = await fetch(url, {
                method: method,
                headers: {
                  "Content-Type": "application/json",
                },
                body: data,
              });
              break
        case 'PUT':
            res = await fetch(url, {
                method: method,
                headers: {
                  "Content-Type": "application/json",
                },
                body: data,
              });
              break
        case 'DELETE':
            res = await fetch(url, {
                method: method,
              });
              break
    }

    const success = res.ok;
  let dataStudent;
  if (success) {
    jsonres = await res.json();
    dataStudent = jsonres.data;
  } else {
    console.log("not found");
    dataStudent = null;
  }
  return dataStudent;

}




  const showData = (data) => {
    let tab = `<tr>
          <th>학번</th>
          <th>이름</th>
          <th>성별</th>
          <th>나이</th>
          <th>전화번호</th>
          <th>거주지역</th>
      </tr>`;
    if (data == null) {
      tab += `<tr>
          <td>no data</td>
          
          </tr>`;
    } else if (data.length > 1) {
      for (let index = 0; index < data.length; index++) {
        tab += `<tr id=${index}>
              <div>
              <td >${data[index].id}</td>
              <td>${data[index].name}</td>
              <td>${data[index].sex}</td>
              <td>${data[index].age}</td>
              <td>${data[index].phone}</td>
              <td>${data[index].location}</td>
              
              </div>
              
              <td><button onclick="editStudent(${index})" >수정</button></td>
             
              <td><button onclick="delStudent(${index})">삭제</button></td>
              </tr>
              `;
      }
    } else {
      tab += `<tr>
              <td>${data.id}</td>
              <td>${data.name}</td>
              <td>${data.sex}</td>
              <td>${data.age}</td>
              <td>${data.phone}</td>
              <td>${data.location}</td>
              <td><button onclick="editStudent(${index})">수정</button></td>
              
              <td><button onclick="delStudent(${index})">삭제</button></td>
              </tr>`;
    }
  
    document.getElementById("studentsData").innerHTML = tab;
  };

  const reload = () => {
    location.reload();
  };

  const getAllStudents = async () => {
    let url = "/api/v1/students";
    const data = await fetchData(url,'GET');
  
    showData(data);
  };

  const searchStudent = async (e) => {
    e.preventDefault();
    
    let searchId = document.getElementById("searchId").value;
  
    if (searchId == "") {
      window.alert("학번을 입력하셔야 합니다.");
    } else {
      let url = `/api/v1/student/${searchId}`;
      const data = await fetchData(url,'GET');
  
      showData(data);
    }
  };

  

  const addStudent = async (e) => {
    e.preventDefault();
    operationType = document.getElementById("addBtn").textContent
    let url = "/api/v1/students";
    let method;
    if (operationType == "저장"){
      method ="PUT"
      console.log("test")
    } else{
      method = "POST"
    }
  
    
    let addForm = document.getElementById("addForm");
    let length = addForm.length;
    let jsonData = "{";
  
    for (i = 0; i < length - 1; i++) {
      if (i > 0) {
        jsonData += ",";
      }
      jsonData += '"' + addForm[i].name + '":"' + addForm[i].value + '"';
    }
  
    jsonData += "}";
  
    //console.log(jsonData)
  
    const res = await fetchData(url, method, jsonData);
    console.log(res);
    
    if (res) {
      if(method == "POST"){
          window.alert("학생이 등록되었습니다.");
      } else if(method == "PUT"){
          window.alert("학생 정보 수정되었습니다.");
      }
      
      reload()
    } else {
      window.alert("아이디가 중복됩니다.\n다른 아이디를 입력해주세요.");
    }
  };

  const delStudent = async (key) => {
    studentName = document.getElementById(key).childNodes[3].textContent;
    studentId = document.getElementById(key).childNodes[1].textContent;
  
    if (confirm(`${studentName}학생을 삭제하시겠습니까?`)) {
        const url = `/api/v1/student/${studentId}`
      const res = await fetchData(url,'DELETE')
      location.reload();
    }
    
  };

  const editStudent = (key) => {
    const form = document.getElementById("addForm");
    document.getElementById("addBtn").textContent="저장"
    document.getElementById("addTitle").textContent="학생 수정"
    for (i = 0; i < form.length - 1; i++) {
      index = 2 * i + 1;
  
      editValue = document.getElementById(key).childNodes[index].textContent;
  
      form[i].value = editValue;
      
      document.getElementById("inputId").disabled = true
    }
  
  
  };


 

searchForm.addEventListener("submit", searchStudent);
addForm.addEventListener("submit",addStudent);