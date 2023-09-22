import { useEffect, useState } from "react"
import Student from "../../../models/student.model"
import { getStudents } from "../../../services/collab.service"

const useStudents = () => {
  const [students, setStudents] = useState<Student[]>([])

  useEffect(() => {
    getStudents()
      .then((response) => setStudents(response.data))
      .catch((error) => console.log(error))
  }, [])

  return {
    students
  }
}

export default useStudents