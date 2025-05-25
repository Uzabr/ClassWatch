import { useState } from "react";
import axios from "axios";
import toast from "react-hot-toast";
import { useNavigate } from "react-router-dom";

const UploadForm = () => {
    const [file, setFile] = useState<File | null>(null);
    const [fileName, setFileName] = useState<string>("");
    const [loading, setLoading] = useState<boolean>(false);
    const navigate = useNavigate();




    const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const selected = e.target.files?.[0];
        if (selected) {
            setFile(selected);
            setFileName(selected.name);
        }
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();

        if (!file) {
            toast.error("Вы не выбрали файл.");
            return;
        }

        if (!file.name.endsWith(".xlsx")) {
            toast.error("Разрешены только файлы формата .xlsx.");
            return;
        }

        const formData = new FormData();
        formData.append("file", file);

        try {
            setLoading(true);

            const response = await axios.post("http://localhost:8080/api/upload/report", formData,{
                headers: { "Content-Type": "multipart/form-data" },
            });

            navigate("/report", { state: response.data });

        } catch (err: any) {
            if (err.response?.status === 415) {
                toast.error("Формат файла не поддерживается.");
            } else if (err.code === "ERR_NETWORK") {
                toast.error("Сервер недоступен. Проверь подключение.");
            } else {
                toast.error("Произошла неизвестная ошибка.");
            }

            console.error("Ошибка при отправке:", err);
        } finally {
            setLoading(false);
        }
    };

    return (
        <form onSubmit={handleSubmit} className="flex flex-col items-center space-y-5 w-full max-w-xl mx-auto">
            <label className="w-full text-sm font-medium text-gray-700">
                Выберите Excel-файл (.xlsx):
                <input
                    type="file"
                    accept=".xlsx"
                    onChange={handleFileChange}
                    className="mt-2 block w-full text-sm text-gray-500 file:mr-4 file:py-2 file:px-4
                     file:rounded-md file:border-0 file:text-sm file:font-semibold
                     file:bg-blue-50 file:text-blue-700 hover:file:bg-blue-100"
                />
            </label>

            {fileName && <p className="text-xs text-gray-600">Выбранный файл: {fileName}</p>}

            <button
                type="submit"
                disabled={loading}
                className={`w-full py-2 px-4 font-semibold rounded-md shadow-md transition active:scale-95
          ${loading ? "bg-gray-400 cursor-not-allowed" : "bg-blue-600 hover:bg-blue-700 text-white"}`}
            >
                {loading ? "Загрузка..." : "Загрузить и проанализировать"}
            </button>

            {loading && (
                <div className="text-blue-600 text-sm animate-pulse font-medium mt-2">
                    ⏳ Обработка файла...
                </div>
            )}

        </form>
    );
};

export default UploadForm;

