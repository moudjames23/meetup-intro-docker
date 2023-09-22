import HttpResponse from '../models/http-response.model'
import Student from '../models/student.model'
import axios from '../plugins/axios'

type GetStudentResponse = HttpResponse<Student>

const baseUrl = 'http://localhost:8080/api/v1'

export const getStudents = () => 
  axios
    .get<GetStudentResponse>(`${baseUrl}/students`)
    .then(response => response.data)