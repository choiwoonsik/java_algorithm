import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.StringTokenizer

/*
10 9
4 1 2 3 4
2 1 5
2 2 6
1 7
1 8
2 7 8
1 9
1 10
2 3 10
1 4
 */
private var N: Int = 0
private var M: Int = 0
private var K: Int = 0
private lateinit var knowingTrueQueue: LinkedList<Int>
private lateinit var knowingTrueSet: MutableSet<Int>
private lateinit var knownPartyRoom: Array<Boolean>
private lateinit var knownParticipant: Array<Boolean>
private lateinit var partyArray: Array<Party>
private lateinit var memberArray: Array<Member>
private data class Party(
    val partyMemberList: MutableList<Int>,
)
private data class Member(
    val partyRoomList: MutableList<Int>,
)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    N = st.nextToken().toInt()
    M = st.nextToken().toInt()
    st = StringTokenizer(br.readLine())
    K = st.nextToken().toInt()

    knowingTrueSet = mutableSetOf()
    knowingTrueQueue = LinkedList()
    partyArray = Array(M + 1) { Party(mutableListOf()) }
    memberArray = Array(N + 1) { Member(mutableListOf()) }
    knownParticipant = Array(N + 1) { false }
    knownPartyRoom = Array(M + 1) { false }

    repeat(K) {
        val member = st.nextToken().toInt()
        knowingTrueSet.add(member)
    }

    repeat(M) { room ->
        st = StringTokenizer(br.readLine())
        val party = Party(mutableListOf())
        val memberCount = st.nextToken().toInt()

        repeat(memberCount) {
            val member = st.nextToken().toInt()
            party.partyMemberList.add(member)
            memberArray[member].partyRoomList.add(room)

            if (knowingTrueSet.contains(member)) {
                knowingTrueQueue.add(room)
            }
        }
        partyArray[room] = party
    }

    while (knowingTrueQueue.isNotEmpty()) {
        val partyRoom = knowingTrueQueue.poll()

        if (knownPartyRoom[partyRoom]) continue
        knownPartyRoom[partyRoom] = true

        for (unknownTrueMember in partyArray[partyRoom].partyMemberList) {
            if (knownParticipant[unknownTrueMember].not()) {
                knownParticipant[unknownTrueMember] = true
                memberArray[unknownTrueMember].partyRoomList.map {
                    if (knownPartyRoom[it].not()) {
                        knowingTrueQueue.add(it)
                    }
                }
            }
        }
    }

    println(knownPartyRoom.count { it.not() } - 1)
}