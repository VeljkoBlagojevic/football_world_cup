package rs.ac.bg.fon.football_world_cup.seed;//package rs.ac.bg.fon.football_world_cup.seed;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import rs.ac.bg.fon.football_world_cup.domain.Mundijal;
//import rs.ac.bg.fon.football_world_cup.repository.MundijalRepository;
//
//@Component
//@RequiredArgsConstructor
//public class MundijalSeeder {
//
//    private final MundijalRepository mundijalRepository;
//
//    public void seedMundijalData() {
//        if (mundijalRepository.count() != 0) {
//            return;
//        }
//        Mundijal mundijal = Mundijal.builder()
//                .naziv("Qatar 2022")
//                .build();
//        mundijalRepository.save(mundijal);
//    }
//}
