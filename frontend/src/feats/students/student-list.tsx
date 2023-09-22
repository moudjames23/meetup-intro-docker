import useStudents from './hooks/use-students.hook'
import Student from './student'

function StudentList() {
  const { students } = useStudents()

  return (
    <div>
      {students.map((student) => (
        <Student key={student.id} {...student} />
      ))}
    </div>
  )
}

export default StudentList
