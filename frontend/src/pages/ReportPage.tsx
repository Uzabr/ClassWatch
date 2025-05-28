import { useLocation } from "react-router-dom";
import { useState } from "react";
import { motion, AnimatePresence } from "framer-motion";

const ReportPage = () => {
    const location = useLocation();
    const result = location.state;
    const [selectedKey, setSelectedKey] = useState<string | null>(null);

    const metrics = [
        { key: "total", label: "Всего студентов", value: result.total },
        { key: "frozen", label: "Заморожены", value: result.frozen },
        { key: "blocked", label: "Заблокированы", value: result.blocked },
        { key: "overdue", label: "Просроченные", value: result.overdue },
        { key: "belowTarget", label: "Не достигли цели", value: result.belowTarget },
        { key: "soonDeadline", label: "Меньше 10 дней", value: result.soonDeadlineCount },
    ];

    const studentMap: Record<string, any[]> = {
        total: result.totalStudents,
        frozen: result.frozenStudents,
        blocked: result.blockedStudents,
        overdue: result.overdueStudents,
        belowTarget: result.belowTargetStudents,
        soonDeadline: result.soonDeadlineStudents,
    };

    const toggleSelection = (key: string) => {
        setSelectedKey((prev) => (prev === key ? null : key));
    };

//     return (
//   <div className="min-h-screen bg-gray-50 flex flex-col md:flex-row gap-8 p-10 transition-all duration-500">
//     <div className="flex-1">
//       <h1 className="text-3xl font-bold text-center md:text-left mb-8">
//         📊 Отчёт по студентам
//       </h1>

//       <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">
//         {metrics.map((m, i) => (
//           <motion.div
//             key={m.key}
//             onClick={() => toggleSelection(m.key)}
//             whileHover={{ scale: 1.05 }}
//             className="cursor-pointer bg-white rounded-xl p-5 shadow-md text-center border border-gray-200 hover:shadow-lg transition"
//           >
//             <h3 className="text-sm text-gray-500 mb-2">{m.label}</h3>
//             <motion.p
//               className="text-2xl font-bold text-blue-700"
//               initial={{ scale: 0 }}
//               animate={{ scale: 1 }}
//               transition={{ delay: 0.1 * i }}
//             >
//               {m.value}
//             </motion.p>
//           </motion.div>
//         ))}
//       </div>
//     </div>

//     {/* Боковая панель справа */}
//     <div className="w-full md:w-[350px] transition-all duration-500">
//       <AnimatePresence mode="wait">
//         {selectedKey ? (
//           <motion.div
//             key={selectedKey}
//             initial={{ opacity: 0, y: 20 }}
//             animate={{ opacity: 1, y: 0 }}
//             exit={{ opacity: 0, y: 10 }}
//             transition={{ type: "spring", duration: 0.5 }}
//             className="bg-white p-6 rounded-xl shadow-2xl border border-blue-200"
//           >
//             <h2 className="text-xl font-semibold mb-4 text-blue-600">
//               📋 {metrics.find((m) => m.key === selectedKey)?.label}
//             </h2>

//             <ul className="space-y-2 max-h-[400px] overflow-y-auto pr-2">
//               <AnimatePresence>
//                 {Array.isArray(studentMap[selectedKey]) &&
//                   studentMap[selectedKey].map((student, i) => (
//                     <motion.li
//                       key={i}
//                       initial={{ opacity: 0, y: 10 }}
//                       animate={{ opacity: 1, y: 0 }}
//                       exit={{ opacity: 0, y: 10 }}
//                       transition={{ delay: i * 0.02 }}
//                       whileHover={{ scale: 1.07 }}
//                       className="text-gray-800 text-sm font-medium bg-gray-50 px-3 py-2 rounded-lg shadow-sm hover:bg-blue-50 transition"
//                     >
//                       {student.login}
//                     </motion.li>
//                   ))}
//               </AnimatePresence>
//             </ul>
//           </motion.div>
//         ) : (
//           <div className="text-sm text-gray-400 text-center mt-4">
//             Нажмите на метрику, чтобы увидеть список
//           </div>
//         )}
//       </AnimatePresence>
//     </div>
//   </div>
// );

      
return (
    <div className="min-h-screen bg-gray-50 flex flex-col md:flex-row gap-8 p-10 transition-all duration-500">
      <div className="flex-1">
        <h1 className="text-3xl font-bold text-center md:text-left mb-8">
          📊 Отчёт по студентам
        </h1>
  
        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">
          {metrics.map((m, i) => (
            <motion.div
              key={m.key}
              onClick={() => toggleSelection(m.key)}
              whileHover={{ scale: 1.05 }}
              className="cursor-pointer bg-white rounded-xl p-5 shadow-md text-center border border-gray-200 hover:shadow-lg transition"
            >
              <h3 className="text-sm text-gray-500 mb-2">{m.label}</h3>
              <motion.p
                className="text-2xl font-bold text-blue-700"
                initial={{ scale: 0 }}
                animate={{ scale: 1 }}
                transition={{ delay: 0.1 * i }}
              >
                {m.value}
              </motion.p>
            </motion.div>
          ))}
        </div>
      </div>
  
      {/* Боковая панель справа */}
      <div className="w-full md:w-[350px] transition-all duration-500">
        <AnimatePresence mode="wait">
          {selectedKey ? (
            <motion.div
              key={selectedKey}
              initial={{ opacity: 0, y: 20 }}
              animate={{ opacity: 1, y: 0 }}
              exit={{ opacity: 0, y: 10 }}
              transition={{ type: "spring", duration: 0.5 }}
              className="bg-white p-6 rounded-xl shadow-2xl border border-blue-200"
            >
              <h2 className="text-xl font-semibold mb-4 text-blue-600">
                📋 {metrics.find((m) => m.key === selectedKey)?.label}
              </h2>
  
              <ul className="space-y-2 max-h-[400px] overflow-y-auto pr-2">
                <AnimatePresence>
                  {Array.isArray(studentMap[selectedKey]) &&
                    studentMap[selectedKey].map((student, i) => (
                      <motion.li
                        key={i}
                        initial={{ opacity: 0, y: 10 }}
                        animate={{ opacity: 1, y: 0 }}
                        exit={{ opacity: 0, y: 10 }}
                        transition={{ delay: i * 0.02 }}
                        whileHover={{ scale: 1.07 }}
                        className="text-gray-800 text-sm font-medium bg-gray-50 px-3 py-2 rounded-lg shadow-sm hover:bg-blue-50 transition"
                      >
                        {student.login}
                      </motion.li>
                    ))}
                </AnimatePresence>
              </ul>
            </motion.div>
          ) : (
            <div className="text-sm text-gray-400 text-center mt-4">
              Нажмите на метрику, чтобы увидеть список
            </div>
          )}
        </AnimatePresence>
      </div>
    </div>
  );
  
};

export default ReportPage;

