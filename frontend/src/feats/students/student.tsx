import StudentType from '../../models/student.model'

type StudentProps = StudentType

function Student({name}: StudentProps) {
  return (
    <div>name: {name}</div>
  )
}

export default Student