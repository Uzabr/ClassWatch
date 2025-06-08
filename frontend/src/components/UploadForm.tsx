
import { useState } from "react";
import axios from "axios";
import toast from "react-hot-toast";
import { useNavigate } from "react-router-dom";

const UploadForm = () => {
  const [file, setFile] = useState<File | null>(null);
  const [fileName, setFileName] = useState<string>("");
  const [loading, setLoading] = useState<boolean>(false);
  const [emailLoading, setEmailLoading] = useState<boolean>(false);
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
      const response = await axios.post("http://localhost:8080/api/upload/report", formData, {
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

  const handleEmailFileChange = async (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    if (!file) return;

    if (!file.name.endsWith(".xlsx")) {
      toast.error("Разрешены только файлы формата .xlsx.");
      return;
    }

    const formData = new FormData();
    formData.append("file", file);

    try {
      setEmailLoading(true);
      const response = await fetch("http://localhost:8080/api/emails/extract", {
        method: "POST",
        body: formData,
      });

      if (!response.ok) throw new Error("Ошибка при извлечении email-ов");

      const blob = await response.blob();
      const url = window.URL.createObjectURL(blob);
      const a = document.createElement("a");
      a.href = url;
      a.download = "emails_extracted.xlsx";
      a.click();
      window.URL.revokeObjectURL(url);
    } catch (err) {
      console.error(err);
      toast.error("Не удалось извлечь email-ы.");
    } finally {
      setEmailLoading(false);
    }
  };

  return (
    <div className="max-w-5xl mx-auto px-6 py-10">
      {/* Заголовок */}
      <h1 className="text-3xl font-bold text-center text-purple-800 mb-10"></h1>

      {/* Карточка 1 — Отчёт */}
      <form
        onSubmit={handleSubmit}
        className="bg-white p-6 rounded-xl shadow-md border border-gray-200 mb-10"
      >
        <h2 className="text-lg font-semibold text-gray-700 mb-4">📊 Загрузить Excel-файл для отчёта</h2>

        <label className="block text-sm font-medium text-gray-700 mb-2">
          Выберите файл:
          <input
            type="file"
            accept=".xlsx"
            onChange={handleFileChange}
            className="mt-2 block w-full text-sm text-gray-500 file:mr-4 file:py-2 file:px-4
              file:rounded-md file:border-0 file:text-sm file:font-semibold
              file:bg-blue-50 file:text-blue-700 hover:file:bg-blue-100"
          />
        </label>

        {fileName && <p className="text-xs text-gray-600 mt-1">Выбранный файл: {fileName}</p>}

        <button
          type="submit"
          disabled={loading}
          className={`mt-4 w-full py-2 px-4 font-semibold rounded-md shadow-md transition active:scale-95
            ${loading ? "bg-gray-400 cursor-not-allowed" : "bg-blue-600 hover:bg-blue-700 text-white"}`}
        >
          {loading ? "Загрузка..." : "Загрузить и проанализировать"}
        </button>

        {loading && (
          <p className="text-blue-600 text-sm animate-pulse font-medium mt-2">⏳ Обработка файла...</p>
        )}
      </form>

      {/* Карточка 2 — Email */}
      <div className="bg-white p-6 rounded-xl shadow-md border border-gray-200">
        <h2 className="text-lg font-semibold text-gray-700 mb-4">📩 Адресная база</h2>

        <input
          type="file"
          accept=".xlsx"
          onChange={handleEmailFileChange}
          disabled={emailLoading}
          className="block w-full text-sm text-gray-500 file:mr-4 file:py-2 file:px-4
            file:rounded-md file:border-0 file:text-sm file:font-semibold
            file:bg-green-50 file:text-green-700 hover:file:bg-green-100"
        />

        {emailLoading && (
          <p className="text-green-600 text-sm animate-pulse font-medium mt-3">⏳ Извлекаем email-ы...</p>
        )}
      </div>
    </div>
  );
};

export default UploadForm;
